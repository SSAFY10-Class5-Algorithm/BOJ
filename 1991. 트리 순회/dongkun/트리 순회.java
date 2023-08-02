import java.util.*;
import java.io.*;
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static Map<String,Node> memo = new HashMap<>();
	
	public static class Node{
		String val;
		Node left;
		Node right;
		
		public Node() {
			// TODO Auto-generated constructor stub
		}
		public Node(String val) {
			this.val = val;
			left = null;
			right = null;
		}
		
		public void setRight(Node r) {
			this.right = r;
		}
		public void setLeft(Node l) {
			this.left = l;
		}
		@Override
		public String toString() {
			return "Node [val=" + val + ", left=" + left + ", right=" + right + "]";
		}
	}
	
	public static void main(String[] args) throws IOException{

		int T = Integer.parseInt(br.readLine());
		
		
		
		while(T-- > 0) {
			String[] temp = br.readLine().split(" ");
			
			Node tempNode;
			
			if(memo.containsKey(temp[0])) {
				tempNode= memo.get(temp[0]);
			}else {
				tempNode = new Node(temp[0]); 
				memo.put(temp[0],tempNode);
			}
			
			if(!temp[1].equals(".")) {
				if(memo.containsKey(temp[1])) {
					tempNode.setLeft(memo.get(temp[1]));
				}else {
					Node leftNode = new Node(temp[1]); 
					tempNode.setLeft(leftNode);
					memo.put(temp[1],leftNode);
				}
			}
			if(!temp[2].equals(".")) {
				if(memo.containsKey(temp[2])) {
					tempNode.setRight(memo.get(temp[2]));
				}else {
					Node rightNode = new Node(temp[2]); 
					tempNode.setRight(rightNode);
					memo.put(temp[2],rightNode);
				}
			}
		}
		
//		for(String n : memo.keySet()) {
//			System.out.println(memo.get(n).toString());
//		}
		
		preorderTraversal(memo.get("A"));
		bw.write("\n");
		inorderTraversal(memo.get("A"));
		bw.write("\n");
		postorderTraversal(memo.get("A"));
		bw.flush();
		bw.close();
	}
	
	public static void preorderTraversal(Node root) throws IOException {
		bw.write(root.val);
		if(root.left != null)preorderTraversal(root.left);
		if(root.right != null)preorderTraversal(root.right);
	}
	public static void inorderTraversal(Node root) throws IOException {
		if(root.left != null)inorderTraversal(root.left);
		bw.write(root.val);
		if(root.right != null)inorderTraversal(root.right);
	}
	public static void postorderTraversal(Node root) throws IOException {
		if(root.left != null)postorderTraversal(root.left);
		if(root.right != null)postorderTraversal(root.right);
		bw.write(root.val);
	}
}


