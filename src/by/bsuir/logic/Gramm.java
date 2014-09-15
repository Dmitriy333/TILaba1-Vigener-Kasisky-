package by.bsuir.logic;

import java.util.ArrayList;
import java.util.List;


public class Gramm {
	private String name;
	private int nod;
	private List<Integer> destinations;
	public Gramm(){
		destinations = new ArrayList<Integer>();
	}
	public Gramm(String name){
		this.name = name;
		destinations = new ArrayList<Integer>();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void addDestValue(int destination){
		destinations.add(destination);
	}
	public void getDestValue(int index){
		destinations.get(index);
	}
	public List<Integer> getDestList(){
		return destinations;
	}
	public int getNod() {
		return nod;
	}
	public void setNod(int nod) {
		this.nod = nod;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(name).append("\n");
		for (Integer integer : destinations) {
			sb.append(integer).append(" ");
		}
		return sb.toString();
	}
	@Override 
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(null == o){
			return false;
		}
		if(getClass() != o.getClass()){
			return false;
		}
		Gramm gramm = (Gramm)o;
		if(this.getName().equals(gramm.getName())){
			return true;
		}
		else{
			return false;
		}
	}
	
}
