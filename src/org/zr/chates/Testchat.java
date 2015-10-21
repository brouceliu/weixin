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
    //�洢����λ��
	private String indexDir="F:/indexDir";
	private String filename="content";
	
	/***analyzer �ִ��� ***/
	public void createIndex(Analyzer analyzer){
		
		String[] contentArr={
			"��������ţ���ÿ��ѧ������",
			"��������������ѧ������������ϵ���",
			"������Ӣ������ѧ���������ϵ꣬ѧ�����ܳ�ɫ"	
		};
		//����������Ŀ¼
		try {
			Directory direct=FSDirectory.open(new File(indexDir));
			IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_4_10_4, analyzer);
			IndexWriter indexWriter=new IndexWriter(direct, conf) ;
			//�������鴴������
			for(String text:contentArr){
				//����document �����field
				Document doc=new Document();
				doc.add(new TextField(filename, text,Field.Store.YES));
				//��doc��ӵ�����
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
		System.out.println("��ѯ���"+query.toString());
		//�������ҳ�����ǰ10�ĵ�
		TopDocs topDocs=searcher.search(query, 10);
		ScoreDoc[] scoreDoc=topDocs.scoreDocs;
		System.out.println("����⵽"+topDocs.totalHits+"��ƥ����");
		for(ScoreDoc sd:scoreDoc){
			Document d=searcher.doc(sd.doc);
			System.out.println(d.get(filename)+"score"+sd.score);
			//�鿴�÷ֽ���
			System.out.println(searcher.explain(query, sd.doc));
		}
		reader.close();
		direct.close();
		
	}
	public static void main(String[] args) throws IOException, ParseException {
		Analyzer analyzer=new IKAnalyzer(true);
		Testchat lt=new Testchat();
		
		lt.createIndex(analyzer);
		lt.searchIndex("�����Ϲ���", analyzer);
	}
	
}
