package org.zr.chates;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Testchat {
    //存储索引位置
	private String indexDir="F:/indexDir";
	private String filename="content";
	
	/***analyzer 分词器 ***/
	public void createIndex(Analyzer analyzer){
		
		String[] contentArr={
			"靠如哈佛剑桥，是每个学生梦想",
			"哈佛是美国著名学府，威武霸气上档次",
			"剑桥是英国著名学府，百年老店，学生都很出色"	
		};
		//创建打开索引目录
		try {
			Directory direct=FSDirectory.open(new File(indexDir));
			IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_4_10_4, analyzer);
			IndexWriter indexWriter=new IndexWriter(direct, conf) ;
			//遍历数组创建索引
			for(String text:contentArr){
				//创建document 并添加field
				Document doc=new Document();
				doc.add(new TextField(filename, text,Field.Store.YES));
				//将doc添加到索引
				indexWriter.addDocument(doc);
				
			}
			indexWriter.commit();
			indexWriter.close();
			direct.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	} 
	
	public void searchIndex(String sentence,Analyzer analyzer) throws IOException, ParseException{
		Directory direct=FSDirectory.open(new File(indexDir));
		IndexReader reader=IndexReader.open(direct);
		IndexSearcher searcher =new IndexSearcher(reader);
		QueryParser parser=new QueryParser(Version.LUCENE_4_10_4, filename, analyzer);
		Query query=parser.parse(sentence);
		System.out.println("查询语句"+query.toString());
		//从索引找出排名前10文档
		TopDocs topDocs=searcher.search(query, 10);
		ScoreDoc[] scoreDoc=topDocs.scoreDocs;
		System.out.println("共监测到"+topDocs.totalHits+"条匹配结果");
		for(ScoreDoc sd:scoreDoc){
			Document d=searcher.doc(sd.doc);
			System.out.println(d.get(filename)+"score"+sd.score);
			//查看得分解析
			System.out.println(searcher.explain(query, sd.doc));
		}
		reader.close();
		direct.close();
		
	}
	public static void main(String[] args) throws IOException, ParseException {
		Analyzer analyzer=new IKAnalyzer(true);
		Testchat lt=new Testchat();
		
		lt.createIndex(analyzer);
		lt.searchIndex("梦想上哈佛", analyzer);
	}
	
}
