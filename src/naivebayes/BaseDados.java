package naivebayes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instance;
import weka.core.Instances;

public class BaseDados {
	
	private String relationName;
	private Enumeration<weka.core.Attribute> listaAtributos;
	private List<String> classes;
	
	private String nomeArquivo;
	//O objetivo é ler um arquivo qualquer no formato arff e salvar o conteúdo dele em uma estrutura de dados
	private BufferedReader dados;
	
	public BaseDados(String nomeArquivo){
		this.nomeArquivo = nomeArquivo;
	}
	
	public void lerArquivo() throws Exception{
			
	    //importa a base de dados ARFF utilizando classes da Weka
	    DataSource source = new DataSource (this.nomeArquivo);
	    Instances data = source.getDataSet();
	    //imprime informações associadas à base de dados
	    System.out.println("Num. instancias:" + data.numInstances());  
	    System.out.println("Num. atributos:" + data.numAttributes());  
	    //imprime o conteúdo da base   
	    //System.out.println("Base de Dados:");
	    //System.out.println(D.toString());

	    for(int i=0;i< data.numAttributes();i++)
	    {
	    	String name = data.attribute(i).name();
	    	int type = data.attribute(i).type();
	    	String typeName = "";
	    	switch(type)
	    	{
	    		case weka.core.Attribute.NUMERIC: typeName = "Numeric"; break;
	    		case weka.core.Attribute.NOMINAL: typeName = "Nominal"; break;
	    		case weka.core.Attribute.STRING: typeName = "String"; break;
	    	}
	    	System.out.println(name+" type "+typeName);
	    }
	    Enumeration<weka.core.Attribute> e = data.enumerateAttributes();
	      /*while (e.hasMoreElements()){
	          System.out.println(e.nextElement().name()); 
	       }*/
	    for (Instance instance : data) {
	    	//System.out.println(instance.attribute(1).value(0));
		}

	
	}

}
