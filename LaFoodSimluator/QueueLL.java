class QueueLL implements Queue
{
	private class qnode
	{
		public Object data;
		public qnode next;
	}
	qnode front, back;
	int count;

	public QueueLL()
	{
		front=back=null;
		count=0;
	}
  
	public void enqueue(Object x)
	{
		qnode newnode=new qnode();
		newnode.data=x;
		newnode.next=null;
		if(isEmpty())
		{ front=newnode; }
		else
		{ back.next=newnode; }
		back=newnode;
		count++;
	}
	public Object dequeue()
	{
		if(isEmpty()) { return null; }
		else
		{
		Object saved=front.data;
		front=front.next;
		count--;
		if(front==null) { back=null; }
		return saved;
		}
	}
	public Object getFront()
	{
		if(isEmpty()) return null;
		else return front.data;
	}
	public void makeEmpty()
	{
		front=back=null;
		count=0;
	}
	public boolean isEmpty() { return count<=0; }
	public boolean isFull() { return false; }
	public int size() { return count; }
}