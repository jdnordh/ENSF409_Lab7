package exercise3;

public class Queue<D> {
	QNode<D> head;
	QNode<D> tail;
	boolean empty;
	
	public Queue(){
		head = null;
		tail = head;
		empty = true;
	}
	
	/** check if the queue is empty */
	public boolean isEmpty(){
		return empty;
	}
	
	/** add another entry to the end */
	public void enQueue(D d){
		if (head != null){
			QNode<D> temp = tail;
			tail = new QNode<D>(d);
			temp.setNext(tail);
		}
		else {
			head = new QNode<D>(d);
			tail = head;
			empty = false;
		}
	}
	
	/** remove and return the first entry */
	public D deQueue(){
		if (head != null){
			D temp = head.getData();
			head = head.next();
			if (head == null) empty = true;
			return temp;
		}
		return null;
	}
}
