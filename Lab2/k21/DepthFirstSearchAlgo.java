package k21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {
	//Task1
	@Override
	public Node execute(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		List<Node> exployred = new ArrayList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
//			System.out.println(current.getLabel());
			if (current.getLabel().equals(goal)) {
				return current;
			} else {
				exployred.add(current);
				List<Node> children = current.getChildrenNodes();
				for (int i = children.size() - 1; i >= 0; i--) {
					Node child = children.get(i);
					if (!frontier.contains(child) && !exployred.contains(child)) {
						frontier.add(child);
						child.setParent(current);
					}
				}
			}
		}
		return null;
	}
	//Task2
	@Override
	public Node execute(Node root, String start, String goal) {
		boolean started = false;
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		List<Node> exployred = new ArrayList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				exployred.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && started) {
				return current;
			} else {
				exployred.add(current);
				List<Node> children = current.getChildrenNodes();
				for (int i = children.size() - 1; i >= 0; i--) {
					Node child = children.get(i);
					if (!frontier.contains(child) && !exployred.contains(child)) {
						frontier.add(child);
						child.setParent(current);
					}
				}
			}
		}
		return null;
	}
	// Task3
	@Override
	public Node executeTreeSearch(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.pop();
			System.out.println(current.getLabel());

			if(current.getLabel().equals(goal)) {
				return current;
			}else {
				List<Node> children = current.getChildrenNodes();
				for (int i = children.size()-1; i >= 0; i--) {
					Node child = children.get(i);
					if(!frontier.contains(child)) {
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
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		while(!frontier.isEmpty()) {
			Node current = frontier.pop();
			System.out.println(current.getLabel());
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				current.setParent(null);
			}
			if(current.getLabel().equals(goal)) {
				return current;
			}else {
				List<Node> children = current.getChildrenNodes();
				for (int i = children.size()-1; i >= 0; i--) {
					Node child = children.get(i);
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
