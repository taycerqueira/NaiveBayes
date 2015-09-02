package naivebayes;

import java.io.BufferedReader;

import weka.core.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.Stats;

public class BaseDados {
	
	private DataSource source;
    private Instances data;
	private String relationName;
	private Enumeration<weka.core.Attribute> listaAtributos;
	private List<String> classes;
	
	private String nomeArquivo;
	//O objetivo � ler um arquivo qualquer no formato arff e salvar o conte�do dele em uma estrutura de dados
	private BufferedReader dados;
	
	public BaseDados(String nomeArquivo) throws Exception{
		this.nomeArquivo = nomeArquivo;
	}
	
	public void lerArquivo() throws Exception{
			
	    //importa a base de dados ARFF utilizando classes da Weka
	    this.source = new DataSource (this.nomeArquivo);
	    this.data = source.getDataSet();
	    //imprime informa��es associadas � base de dados
	    System.out.println("Num. instancias:" + data.numInstances());  
	    System.out.println("Num. atributos:" + data.numAttributes());  
	    //imprime o conte�do da base   
	    //System.out.println("Base de Dados:");
	    //System.out.println(D.toString());
	    
      	    
       // getAtributos();
	    Enumeration<Attribute> e = data.enumerateAttributes();
	    
	    while(e.hasMoreElements()){
	    	
	    	Attribute at = e.nextElement();
	    	System.out.println(at.name());
	    	
	    	if(at.name().equals("class")){
	    		int a = at.numValues();
	    		for(int i = 0; i < a; i++)
	    			System.out.println(at.value(i));
	    		System.out.println(data.attributeStats(at.index()).nominalCounts[0]+"");
	    		
	    		System.out.println(at.indexOfValue("Iris-virginica"));
	    	}else if(at.name().equals("sepallength")){
	    		double [] st = data.attributeToDoubleArray(at.index());
	    		Arrays.sort(st);
	    		System.out.println(st);
	    		
	    	}
	    	
	    }
	      /*while (e.hasMoreElements()){
	          System.out.println(e.nextElement().name()); 
	       }*/
	    for (Instance instance : data) {
	    	//System.out.println(instance.attribute(1).value(0));
		}

	
	   
	    	
	    
	}
	
	public void getAtributos(){
	
	    
	}
	
	public List<String> listAtributos(){
		
		List<String> atributos = new ArrayList<String>(); 
		
		while(this.listaAtributos.hasMoreElements()){
			atributos.add(this.listaAtributos.nextElement().name());
		}
		
		return atributos;
	}
	
	
	
	public Instances getInstances(){
		return this.data;
	}

}
