package com.trabajofinal.runnable;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.trabajofinal.model.Consulta;

public class MyRunnable implements Runnable {

	public static int myCount = 0;
	private long initialTime;
	private Consulta consulta;
	private SingleConnectionDataSource ds;
	List<Map<String, Object>> result;

    public MyRunnable(){         
    }

	public MyRunnable(Consulta consulta,  SingleConnectionDataSource ds, long initialTime) {
		this.initialTime = initialTime;
		this.consulta = consulta;
		this.ds = ds;
	}

	@Override //Sobreescribo el metodo run
    public void run(){
    	System.out.println((System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
    	
    	while(MyRunnable.myCount <= 10){
    	    Thread t = Thread.currentThread();
    	    System.out.println("Hilo actual: " + t);
    		
            try{
        		result = consulta.ejecutarQuery(ds, consulta);
                System.out.println("Expl Thread: "+(++MyRunnable.myCount));
                Thread.sleep(100);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    }
    
    @Autowired
	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
    
    public List<Map<String, Object>> getResultado(){
    	/*MyRunnable proceso2 = new MyRunnable(this.consulta,this.ds,this.initialTime);
    	Thread h2 = new Thread(proceso2);
    	h2.start();
    	*/
        MyRunnable runobject = new MyRunnable();
    	return runobject.result;
    }

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
  }
