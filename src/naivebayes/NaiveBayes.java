package naivebayes;

import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class NaiveBayes {
	
	private DataSource data;
	private Instances instances;
	
	public NaiveBayes(DataSource data, Instances instances) {
		super();
		this.data = data;
		this.instances = instances;
	}
	
	
	public double calcularProbabilidade(String value, String atributo, String classe){
		
		return 0;
		
	}
	
	
	
	public int getTotalPorClasse(String classe){
		
		Attribute at = instances.attribute("class"); // pega o atributo class que é nominal
		AttributeStats status = instances.attributeStats(at.index()); // pega o status do atributo - contem dados de qtd de um valor por atributo
		
		int index = at.indexOfValue(classe); // pega o indice da classe que deseja analise, por exemplo: Iris-setosa 
		int value = status.nominalCounts[index]; // pega a quantidade de instancias que tem Iris-setosa como classe
	
		return value;
	}

}
