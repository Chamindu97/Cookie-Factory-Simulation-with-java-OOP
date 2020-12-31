package cookieFactory;

import java.io.BufferedWriter;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;

public class FactoryRunner {
	
	public static ArrayList<String> cookieFactory = new ArrayList<String>();
	public static ArrayList<String> invoice = new ArrayList<String>();
	public static ArrayList<String> rmArrival = new ArrayList<String>();
	
	public static ArrayList<Integer> warehouseInventory = new ArrayList<Integer>();      // FL=0, BT=1, CH=2, MK=3, SG=4, GN=5
	public static ArrayList<Integer> merchandiseInventory = new ArrayList<Integer>();	 // BB=0, BC=1, CC=2, GC=3
	public static ArrayList<Integer> BBrecipe = new ArrayList<Integer>();			     // FL=0, BT=1, CH=2, MK=3, SG=4, GN=5, time=6
	public static ArrayList<Integer> BCrecipe = new ArrayList<Integer>();				 // FL=0, BT=1, CH=2, MK=3, SG=4, GN=5, time=6
	public static ArrayList<Integer> CCrecipe = new ArrayList<Integer>();				 // FL=0, BT=1, CH=2, MK=3, SG=4, GN=5, time=6
	public static ArrayList<Integer> GCrecipe = new ArrayList<Integer>();				 // FL=0, BT=1, CH=2, MK=3, SG=4, GN=5, time=6
	public static ArrayList<Integer> tempInvoice = new ArrayList<Integer>();			 // BB=0, BC=1, CC=2, GC=3, time=4
	public static ArrayList<Integer> tempArrival = new ArrayList<Integer>(); 			 // FL=0, BT=1, CH=2, MK=3, SG=4, GN=5, time=6
	public static ArrayList<Integer> missing = new ArrayList<Integer>();			 // BB=0, BC=1, CC=2, GC=3, time=4
	
	
	public static int warehouseCapacity = 0;
	public static int merchandiseCapacity = 0;
	public static int Runtime = 0;
	public static int Productionlines = 0;
	public static int tempTime = 0;
	public static double remainingTime = 0;
	public static int state = 1;
	public static int total;
	public static int sum;
	public static int invoice_num = 0;
	public static int total_invoices = 0;
	public static int invoice_count = 0;
	public static int total_dispatched = 0;
	public static int total_manufactured = 0;
	public static int total_requirment = 0;
	public static float ETO;
	
	static long startedTime;
	
	static InventoryManager IM = new InventoryManager();
	static FactoryManager FM = new FactoryManager();
	
	public static final String FILENAME = "processReport.txt ";
	
	
	public static void main(String[] args){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
		Date date = new Date();
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))){
			bw.write("+--------------------------------------------------------------------------------+");
			bw.newLine();
			bw.write("|                  JAVA COOKIE FACTORY OPERATION REPORT                          |");
			bw.newLine();
			bw.write("+--------------------------------------------------------------------------------+");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String line = null;
		
		String path1 = args[0];
		String path2 = args[1];
		String path3 = args[2];
		
		
		try {
			FileReader fileReader1 = new FileReader(path2.replace("/", "\\").replace("\\", "\\\\"));
			BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
			while ((line = bufferedReader1.readLine()) != null) {
				invoice.add(line);
			}
		bufferedReader1.close();
//-------------------------------------------------------------------------------------------------------------------		
			FileReader fileReader2 = new FileReader(path3.replace("/", "\\").replace("\\", "\\\\"));
			BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
			while ((line = bufferedReader2.readLine()) != null) {
				rmArrival.add(line);
			}
			bufferedReader2.close();
//-------------------------------------------------------------------------------------------------------------------
			FileReader fileReader3 = new FileReader(path1.replace("/", "\\").replace("\\", "\\\\"));
			BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
			while ((line = bufferedReader3.readLine()) != null) {
				cookieFactory.add(line);
			}
			bufferedReader3.close();
		} 
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open the file");
		}
		catch(IOException ex) {
			System.out.println("Error reading file");
		}
		
		FM.updateInfo();
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
			bw.newLine();
			bw.write("Start time: " + dateFormat.format(date));
			bw.newLine();
			bw.write("Production line: " + Productionlines);
			bw.newLine();
			bw.write("Run time: " + Runtime);
			bw.newLine();
			bw.write("Warehouse storage capacity: " + warehouseCapacity);
			bw.newLine();
			bw.write("Factory storage capacity: " + merchandiseCapacity);
			bw.newLine();
			bw.newLine();
			bw.write("Inventory Manager initiated….");
			bw.newLine();
			bw.write("Factory Manager Initiated….");
			bw.newLine();
			bw.newLine();
			bw.write("Warehouse inventory populated…..");
			bw.newLine();
			bw.write("flour - " +warehouseInventory.get(0));
			bw.newLine();
			bw.write("Butter - " +warehouseInventory.get(1));
			bw.newLine();
			bw.write("Chocolate Chips - "+warehouseInventory.get(2));
			bw.newLine();
			bw.write("Milk - "+warehouseInventory.get(3));
			bw.newLine();
			bw.write("Sugar - "+warehouseInventory.get(4));
			bw.newLine();
			bw.write("Ginger - "+warehouseInventory.get(5));
			bw.newLine();
			bw.newLine();
			bw.write("Merchandise inventory populated…..");
			bw.newLine();
			bw.write("Base Biscuits - " +merchandiseInventory.get(0));
			bw.newLine();
			bw.write("Butter Cookies - " +merchandiseInventory.get(1));
			bw.newLine();
			bw.write("Chocolate Chip Cookies - "+merchandiseInventory.get(2));
			bw.newLine();
			bw.write("Ginger Cookies - "+merchandiseInventory.get(3));
			bw.newLine();
			bw.newLine();
			bw.write("Factory Started….");
			bw.newLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
//~~~~~~~~~~going to use invoice thread here~~~~~~~~~~~~~~~		
	Thread t1 = new Thread() {
		@Override
		public void run() {
		for (int i=0; i<invoice.size(); i++) {
			if(i==0) {
				FM.updateInvoice1(i);
			}else {
				FM.updateInvoice2(i);
			}
			if (IM.invoiceChecker(i) == true) {
				int BB,BC,CC,GC;
				BB = missing.get(0);
				BC = missing.get(1);
				CC = missing.get(2);
				GC = missing.get(3);
				BBmake(BB);
				if(state == 1) {
					if(Thread.interrupted()) {
						return;
					}
					BCmake(BC);
					if (state == 1) {
						if(Thread.interrupted()) {
							return;
						}
						CCmake(CC);
						if (state == 1) {
							if(Thread.interrupted()) {
								return;
							}
							GCmake(GC);
							if (state == 1) {
								if(Thread.interrupted()) {
									return;
								}
								remaining();
								if(((long)tempInvoice.get(4)+startedTime) - (System.currentTimeMillis()%10000)<0) {
									if(Thread.interrupted()) {
										return;
									}
									FM.completeInvoice(i);
								}else {
									if(Thread.interrupted()) {
										return;
									}
									try {
										Thread.sleep(((long)tempInvoice.get(4)+startedTime) - (System.currentTimeMillis()%10000));
									} catch (InterruptedException e) {
										return;
									}
									FM.completeInvoice(i);
								}
							}else {
								state = 1;
							}
						}else {
							state = 1;
						}
					}else {
						state = 1;
					}
				}else {
					state = 1;
				}
			}else {
				System.out.println("invoice can not");
				System.out.println(" \n \n");
			}
		invoice_count = i+1;	
		
		}
		}
	};
		
//~~~~~~~~~ going to use arrival thread here~~~~~~~~~~~~~~~	
	
	Thread t2 = new Thread() {
		@Override
		public void run() {
		for(int i =0 ; i < rmArrival.size(); i++) {
			if(i == 0) {
				FM.updateArrival1(i);
			}else {
				FM.updateArrival2(i);
			}
			if(((long)tempArrival.get(6)+startedTime) - (System.currentTimeMillis()%10000)<0) {
				if(Thread.interrupted()) {
					return;
				}
				IM.arrivalChecker();
			}else {
			if(Thread.interrupted()) {
				return;
			}
			try {
				Thread.sleep(((long)tempArrival.get(6)+startedTime) - (System.currentTimeMillis()%10000));
			} catch (InterruptedException e) {
				return;
			}
			IM.arrivalChecker();
			}
		}
		}
	};
	    startedTime = System.currentTimeMillis()%10000;
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(Runtime);
			t1.interrupt();
			t2.interrupt();
		} catch (InterruptedException ex) {
			
		}
		
		ETO = (((float)total_dispatched/total_requirment)*100);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
			bw.newLine();
			bw.newLine();
			bw.write("Total manufactured " + "[" +total_manufactured + "]");
			bw.newLine();
			bw.write("Total dispatched " + "[" +total_dispatched + "]");
			bw.newLine();
			bw.write("Invoice completed " + total_invoices);
			bw.newLine();
			bw.write("Total requirement " +"["+ total_requirment+"]");
			bw.newLine();
			bw.write("Effective throughput " +ETO);
			bw.newLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void BBmake(int Amount) {
		if(IM.merchandiseChecker1(Amount)) {
			IM.warehouseCheckerBB(Amount);
		}else {
			state = 0;
		}
	}
	
	public static void BCmake(int Amount) {
		if(IM.merchandiseChecker1(Amount)) {
			IM.warehouseCheckerBC(Amount);
		}else {
			state = 0;
		}
	}
	
	public static void CCmake(int Amount) {
		if(IM.merchandiseChecker1(Amount)) {
			IM.warehouseCheckerCC(Amount);
		}else {
			state = 0;
		}
	}
	
	public static void GCmake(int Amount) {
		if(IM.merchandiseChecker1(Amount)) {
			IM.warehouseCheckerGC(Amount);
		}else {
			state = 0;
		}
	}
	
	public static void remaining() {
		int make;
		double per1kg;
		
		
		IM.warehouseChecker();
		
		if (sum <= ((3*warehouseCapacity)/4)) {
		
		per1kg = ((double)BBrecipe.get(6)/100)/Productionlines;
		make = (int) ((remainingTime/4)/per1kg);
		IM.merchandiseCheckerBB(make);
		
		per1kg = ((double)BCrecipe.get(6)/100)/Productionlines;
		make = (int) ((remainingTime/4)/per1kg);
		IM.merchandiseCheckerBC(make);
		
		per1kg = ((double)CCrecipe.get(6)/100)/Productionlines;
		make = (int) ((remainingTime/4)/per1kg);
		IM.merchandiseCheckerCC(make);
		
		per1kg = ((double)GCrecipe.get(6)/100)/Productionlines;
		make = (int) ((remainingTime/4)/per1kg);
		IM.merchandiseCheckerGC(make);
		
		}
	}
	
	
}