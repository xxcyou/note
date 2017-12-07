public class JcThread extends Thread
{
	private int i;int l;int p=0;
	JcThread(int i,int l){
		this.i=i;
		this.l=l;
	}
	public void run()
	{
		try{
			while(++p!=i){
				System.out.println("我是多线程"+l+"循环"+p);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// TODO: Implement this method
	}
}
