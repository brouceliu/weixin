package org.zr.chat;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.zr.chates.ChatAction;
import org.zr.entity.Knowledge;
import org.zr.model.dao.ChatDao;
import org.zr.model.dao.ChatDaoImpl;
import org.zr.service.CoreService;

public class ChatService {
	private static Logger log = LoggerFactory.getLogger(ChatService.class);
	public static String getIndexDir() {
		String path = ChatService.class.getResource("/").getPath();
		path = path.replace("%20", " ");
		return path + "index/";
	}

	// 创建索引
	public static void createIndex() {
		ChatDao chad = new ChatDaoImpl();
		List<Knowledge> kolist = chad.findAllKnowledge();
		Directory diret = null;
		IndexWriter indexWrite = null;
		try {
			diret = FSDirectory.open(new File(getIndexDir()));
			IndexWriterConfig iwConfig = new IndexWriterConfig(
					Version.LUCENE_4_10_4, new IKAnalyzer(true));
			indexWrite = new IndexWriter(diret, iwConfig);
			Document doc = null;
			for (Knowledge knowledge : kolist) {
				doc = new Document();
				doc.add(new TextField("question", knowledge.getQuestion(),
						Store.YES));
				doc.add(new IntField("id", knowledge.getId(), Store.YES));
				doc.add(new StringField("answer", knowledge.getAnswer(),
						Store.YES));
				doc.add(new IntField("category", knowledge.getCategory(),
						Store.YES));
				indexWrite.addDocument(doc);
			}
			indexWrite.close();
			diret.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// 从索引中找答案
	private static Knowledge searchIndex(String content) {
		Knowledge knowledge = null;
		try {
			Directory direct = FSDirectory.open(new File(getIndexDir()));
			IndexReader reader = IndexReader.open(direct);
			IndexSearcher Search = new IndexSearcher(reader);
			QueryParser queryparser = new QueryParser(Version.LUCENE_4_10_4,
					"question", new IKAnalyzer(true));
			Query query = queryparser.parse(QueryParser.escape(content));
			TopDocs topdocs = Search.search(query, 1);
			log.info("top hits is "+topdocs.totalHits);
			if (topdocs.totalHits > 0) {
				knowledge = new Knowledge();
				ScoreDoc[] scoredoc = topdocs.scoreDocs;
				for (ScoreDoc sd : scoredoc) {
					Document doc = Search.doc(sd.doc);
					knowledge.setId(doc.getField("id").numericValue()
							.intValue());
					knowledge.setQuestion(doc.get("question"));
					knowledge.setAnswer(doc.get("answer"));
					knowledge.setCategory(doc.getField("category")
							.numericValue().intValue());

				}
			}
			reader.close();
			direct.close();
		} catch (IOException | ParseException e) {
			knowledge = null;
			e.printStackTrace();
		}

		return knowledge;
	}

	// 根据question 返回answer
	public static String chat(String openid, String createtime, String question) {
		String answer = null;
		int chatcategory = 0;
        Knowledge knowledge=searchIndex(question);
        if(null !=knowledge){
        	//笑话
        	if(2==knowledge.getCategory()){
        		answer=ChatAction.getJoker();
        		chatcategory=2;
        	}
        
        
        //上下文
        else if(3==knowledge.getCategory()){
        	//判断上次聊天类别
        	int category=ChatAction.getLastCategory(openid);
        	if(2==category){
        		answer=ChatAction.getJoker();
        		chatcategory=2;
        	}else{
        		answer=knowledge.getAnswer();
        		chatcategory=knowledge.getCategory();
        	}
        }
        //普通对话
        else{
        	answer=knowledge.getAnswer();
        	if("".equals(answer)){
        		answer=ChatAction.getAnswerBySub(knowledge.getId());
        		chatcategory=1;
        	}
        }
        
        }else{
        	answer=getDefault();
        	chatcategory=0;
        }
        ChatAction.Save(openid, createtime, question, answer, chatcategory);
		return answer;

	}
	
	//获取默认答案
	private static String getDefault(){
		String[] answer={
				"我们聊别的吧！","你到底说啥？","没听懂你的意思","不明白，我一头雾水"
		};
		return answer[getRandomNumber(answer.length)];
		
		
	}
	private static int getRandomNumber(int length){
		Random rand=new Random();
		return rand.nextInt(length);
	}

}
