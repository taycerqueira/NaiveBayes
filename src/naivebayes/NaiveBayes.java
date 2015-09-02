package naivebayes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class NaiveBayes {
	
	private DataSource data;
	private Instances instances;
	
	public NaiveBayes() {
		super();
	}
	
	public void lerDados(String nomeArquivo) throws Exception{
		this.data = new DataSource (nomeArquivo);
	    this.instances = data.getDataSet();
	   
	}
	
	public double calcularProbabilidade(String value, String atributo, String classe){
		
		
		int totalClasse = getTotalClasse(classe);
		int totalValor = getTotal(value, atributo, classe);
		
		return totalValor/(double)totalClasse;
		
	}
	
	public int getTotal(String value, String atributo, String classe){
		
		Attribute at = instances.attribute(atributo); 
		if(at.isNumeric()){
			Double v = Double.parseDouble(value);
			return getTotalNumero(v, at, classe);// pega o total de ocorrencias de um número
		}else{
			AttributeStats status = instances.attributeStats(at.index()); // pega o status do atributo - contem dados de qtd de um valor por atributo
			int index = at.indexOfValue(value); // pega o indice da classe que deseja analise, por exemplo: Iris-setosa 
			return status.nominalCounts[index]; // pega a quantidade de instancias que tem Iris-setosa como classe
		}
	}
	
	private int getTotalClasse(String classe){
		Attribute at = instances.attribute("class"); 
		AttributeStats status = instances.attributeStats(at.index()); // pega o status do atributo - contem dados de qtd de um valor por atributo
		int index = at.indexOfValue(classe); // pega o indice da classe que deseja analise, por exemplo: Iris-setosa 
		return status.nominalCounts[index]; // pega a quantidade de instancias que tem Iris-setosa como classe
	}
	
	private int getTotalNumero(Double v, Attribute attribute, String classe) {
		
		Enumeration<Instance> lines = instances.enumerateInstances();
		int index_class = instances.attribute("class").index();
		int qtd = 0;
		
		while(lines.hasMoreElements()){
			
			Instance line = lines.nextElement();
			double value = line.value(attribute);
			String aux_classe = line.stringValue(index_class);
			
			if(classe.equals(aux_classe) && value == v){
				qtd++;
			}
		}
		
		return qtd;
	}
	

	public void processar(){
		
		double por = calcularProbabilidade("5.1", "sepallength", "Iris-setosa");
		System.out.println(por);
	}

}
