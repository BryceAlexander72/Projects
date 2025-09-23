interface Queue extends DataStructure
{
    public void enqueue(Object x);
    public Object dequeue();
    public Object getFront();
}