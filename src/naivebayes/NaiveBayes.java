package naivebayes;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		
		return totalValor/(double)totalClasse;// calcula a probabilidade do valor na classe no atributo 	
	}
	
	public double calcularProbabilidadeConjunto(String classe){
		
		int totalClasse = getTotalClasse(classe);
		int total = getTotal(); 
		
		return totalClasse/(double)total;
	}
	
	public double calcularProbabilidadeValor(String value, String atributo){
		
		int totalValor = getTotalValor(value, atributo);
		int total = getTotal();
		
		return totalValor/(double)total; // retorna a probabilidade de incidencia do valor na base toda.
		
	}
	
	/*Calcula a probabilidade por para o atributo e classe*/
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
	
	/*Calcula a quantidade de um valor em um atributo*/
	public int getTotalValor(String value, String atributo){
		
		Attribute at = instances.attribute(atributo); 
		if(at.isNumeric()){
			Double v = Double.parseDouble(value);
			return getTotalNumero(v, at); // calcula a ocorrencia de value no total da base
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
	
	private int getTotal(){
		return this.instances.size();
	}
	
	private int getTotalNumero(Double v, Attribute attribute, String classe) {
		
		Enumeration<Instance> lines = instances.enumerateInstances(); // pega todas a linhas de dados
		int index_class = instances.attribute("class").index();// pega o indice do atributo class
		int qtd = 0;
		
		while(lines.hasMoreElements()){
			
			Instance line = lines.nextElement();
			double value = line.value(attribute);// pega o valor do atributo
			String aux_classe = line.stringValue(index_class);//pega o nome da classe da linha
			
			if(classe.equals(aux_classe) && value == v){
				qtd++;
			}
		}
		return qtd;
	}
	
	private int getTotalNumero(Double v, Attribute attribute){
		
		double [] datas = instances.attributeToDoubleArray(attribute.index());
		Arrays.sort(datas);
		int qtd = 0;
		
		for(double data : datas){
			if(data == v){
				qtd++;
			}
		}
		return qtd;
	}
	

	public void processar(List<String> values){
		
		Attribute at = instances.attribute("class"); 
		int numClasses = at.numValues();
		String classeFinal = "";
		
		double [] result = new double[numClasses];
		List<String> attributes = getAttributes();
		
		for(int i = 0; i < numClasses; i++){
		
			double probabilidade = 1;
			String classe = at.value(i);
			
			for(int j = 0; j < attributes.size(); j++){
				double v = calcularProbabilidade(values.get(j), attributes.get(j), classe);
				probabilidade = probabilidade * v;
			}
			result[i] = probabilidade;
		}
		
		double max = 0;
		
		for(int i = 0; i < numClasses; i++){
			if(result[i] >= max){
				max = result[i];
				classeFinal = at.value(i);
			}
		}
		
		
		System.out.println("Classe é = "+classeFinal);
	}

	public List<String> getAttributes() {
	
		List<String> ats = new ArrayList<String>(); 
		Enumeration<Attribute> c = this.instances.enumerateAttributes(); 
		
		while(c.hasMoreElements()){
			Attribute at = c.nextElement();
			if(!at.name().equals("class"))
				ats.add(at.name());
		}
		
		
		return ats;
	}

}
