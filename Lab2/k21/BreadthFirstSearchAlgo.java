package k21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo{
	// Task1
	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(root);
		List<Node> exployred = new ArrayList<Node>();
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}else {
				exployred.add(current);
				List<Node> children = current.getChildrenNodes();
				for (Node node : children) {
					if(!frontier.contains(node) && !exployred.contains(node)) {
						frontier.add(node);
						node.setParent(current);
					}
				}
			}
		}
		
		return null;
	}
	// Task2
	@Override
	public Node execute(Node root, String start, String goal) {
		boolean started = false;
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(root);
		List<Node> exployred = new ArrayList<Node>();
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				exployred.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal) && started) {
				return current;
			}else {
				exployred.add(current);
				List<Node> children = current.getChildrenNodes();
				for (Node node : children) {
					if(!frontier.contains(node) && !exployred.contains(node)) {
						frontier.add(node);
						node.setParent(current);
					}
				}
			}
		}
		
		return null;
	}
	// Task3
	public Node executeTreeSearch(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}else {
				List<Node> children = current.getChildrenNodes();
				System.out.println(current.getLabel()+": "+children.size());
				for (Node child : children) {
					if(!frontier.contains(child) ) {
						frontier.add(child);
							child.setParent(current);
						}
				}
			}
		}
		return null;
	}
	@Override
	public Node executeTreeSearch(Node root, String start, String goal) {
		boolean started = false;
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal) && started) {
				return current;
			}else {
				List<Node> children = current.getChildrenNodes();
				System.out.println(current.getLabel()+": "+children.size());
				for (Node child : children) {
					if(!frontier.contains(child)) {
						frontier.add(child);
						child.setParent(current);
					}
				}
			}
		}
		return null;
	}
}
