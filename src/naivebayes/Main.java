package naivebayes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		
		NaiveBayes naive = new NaiveBayes();
		try {
		
			naive.lerDados("iris.arff");
			List<String> attributes = naive.getAttributes(); 
			List<String> values = new ArrayList<String>(); 
			Scanner sc = new Scanner(System.in); 
			
			for(String at : attributes){
				System.out.println("Digite o valor do atributo "+at);
				String v = sc.nextLine();
				values.add(v);
			}
			
			naive.processar(values);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
