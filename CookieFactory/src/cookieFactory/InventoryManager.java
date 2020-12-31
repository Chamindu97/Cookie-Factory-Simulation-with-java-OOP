package cookieFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InventoryManager extends FactoryRunner {
	
	FactoryRunner FR = new FactoryRunner();
	
	public boolean invoiceChecker(int j) {
		int temp=0;
		int i;
		double BB = 0, BC = 0, CC = 0, GC = 0;
		double estimated_time = 0;
		if(j==0) {
			for(i = 0; i<merchandiseInventory.size(); i++) {
				temp = merchandiseInventory.get(i) - tempInvoice.get(i);
				if(temp < 0) {
					switch (i) {
						case 0 :
							BB = ((double)BBrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.add(0, temp*(-1));
							break;
						case 1 :
							BC = ((double)BCrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.add(1, temp*(-1));
							break;
						case 2 :
							CC = ((double)CCrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.add(2, temp*(-1));
							break;
						case 3 :
							GC = ((double)GCrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.add(3, temp*(-1));
							break;
					}
				}else{
					switch (i) {
						case 0 :
							missing.add(0, 0);
							break;
						case 1 :
							missing.add(1, 0);
							break;
						case 2 :
							missing.add(2, 0);
							break;
						case 3 :
							missing.add(3, 0);
							break;
					}
				}
			}
		}else {
			for(i = 0; i<merchandiseInventory.size(); i++) {
				temp = merchandiseInventory.get(i) - tempInvoice.get(i);
				if(temp < 0) {
					switch (i) {
						case 0 :
							BB = ((double)BBrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.set(0, temp*(-1));
							break;
						case 1 :
							BC = ((double)BCrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.set(1, temp*(-1));
							break;
						case 2 :
							CC = ((double)CCrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.set(2, temp*(-1));
							break;
						case 3 :
							GC = ((double)GCrecipe.get(6)/100)*(temp*(-1))/Productionlines;
							missing.set(3, temp*(-1));
							break;
					}
				}else{
					switch (i) {
						case 0 :
							missing.set(0, 0);
							break;
						case 1 :
							missing.set(1, 0);
							break;
						case 2 :
							missing.set(2, 0);
							break;
						case 3 :
							missing.set(3, 0);
							break;
					}
				}
			}
		}
		
		estimated_time = (BB+BC+CC+GC);
		if(estimated_time <= (tempInvoice.get(4) - tempTime)) {
			remainingTime = (tempInvoice.get(4) - tempTime) - estimated_time;
			return true;
		}else{
			return false;
		}
	}
	
	public void arrivalChecker() {
		warehouseChecker();
		for(int i = 0; i < (tempArrival.size() - 1); i++ ) {
			sum = sum + tempArrival.get(i);
		}
		if(sum <= warehouseCapacity) {
			for(int i =0; i < warehouseInventory.size(); i++) {
				if(tempArrival.get(i) != 0) {
					String name = null;
					warehouseInventory.set(i, (warehouseInventory.get(i)+tempArrival.get(i)));
					switch(i) {
						case 0:
							name = "Flour ";
							break;
						case 1:
							name = "Butter ";
							break;
						case 2:
							name = "Chocolate Chips ";
							break;
						case 3:
							name = "Milk ";
							break;
						case 4:
							name = "Sugar ";
							break;
						case 5:
							name = "Ginger ";
							break;
					}
					try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("RM Arrival " + name + tempArrival.get(6) + " " + tempArrival.get(i) + " " +"[" + warehouseInventory.get(i)+"]");
						bw.newLine();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}else {
					warehouseInventory.set(i, (warehouseInventory.get(i)+tempArrival.get(i)));
				}
				
				
			}
		}else {
			System.out.println("Inventory full..!");
		} 
	}
	
	public boolean merchandiseChecker1(int Amount) {
		total = 0;
		for(int i =0; i<4; i++) {
			total = total + merchandiseInventory.get(i);
		}
		total = total+Amount;
		if(total <= merchandiseCapacity) {
			return true;
		}else{
			return false;
		}
	}
 	
	public void merchandiseCheckerBB(int make) {
		if(total < merchandiseCapacity) {
			if( make <= ((merchandiseCapacity - total)/4) ) {
				if(materialsCheckerBBmake(make)) {
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((BBrecipe.get(i)/100)*make)));
					}
					merchandiseInventory.set(0, merchandiseInventory.get(0)+make);
					total_manufactured = total_manufactured+make;
			/*		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Base Biscuits " + make + "Kg " + "Processed: " + (((double)BBrecipe.get(6)/100*make)/Productionlines) + "ms " +"["+ merchandiseInventory.get(0)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks1");
				}
			}else {
				if(materialsCheckerBBrand((merchandiseCapacity - total)/4)){
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((BBrecipe.get(i)/100)*(merchandiseCapacity - total)/4)));
					}
					merchandiseInventory.set(0, merchandiseInventory.get(0)+(merchandiseCapacity - total)/4);
					total_manufactured = total_manufactured+((merchandiseCapacity - total)/4);
			/*		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Base Biscuits " + ((merchandiseCapacity - total)/4) + "Kg " + "Processed: " + (((double)BBrecipe.get(6)/100*((merchandiseCapacity - total)/4))/Productionlines) + "ms " +"["+ merchandiseInventory.get(0)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks2");
				}
			}
		}
	}
	
	public void merchandiseCheckerBC(int make) {
		if(total < merchandiseCapacity) {
			if( make <= ((merchandiseCapacity - total)/4) ) {
				if(materialsCheckerBCmake(make)) {
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((BCrecipe.get(i)/100)*make)));
					}
					merchandiseInventory.set(1, merchandiseInventory.get(1)+make);
					total_manufactured = total_manufactured+make;
			/*		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Butter Cookies " + make + "Kg " + "Processed: " + (((double)BCrecipe.get(6)/100*make)/Productionlines) + "ms " +"["+ merchandiseInventory.get(1)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks1");
				}
			}else {
				if(materialsCheckerBCrand((merchandiseCapacity - total)/4)){
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((BCrecipe.get(i)/100)*(merchandiseCapacity - total)/4)));
					}
					merchandiseInventory.set(1, merchandiseInventory.get(1)+(merchandiseCapacity - total)/4);
					total_manufactured = total_manufactured+((merchandiseCapacity - total)/4);
			/*		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Butter Cookies " + ((merchandiseCapacity - total)/4) + "Kg " + "Processed: " + (((double)BCrecipe.get(6)/100*((merchandiseCapacity - total)/4))/Productionlines) + "ms " +"["+ merchandiseInventory.get(1)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks2");
				}
			}
		}
	}
	
	public void merchandiseCheckerCC(int make) {
		if(total < merchandiseCapacity) {
			if( make <= ((merchandiseCapacity - total)/4) ) {
				if(materialsCheckerCCmake(make)) {
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((CCrecipe.get(i)/100)*make)));
					}
					merchandiseInventory.set(2, merchandiseInventory.get(2)+make);
					total_manufactured = total_manufactured+make;
				/*	try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Chocolate Chips Cookies " + make + "Kg " + "Processed: " + (((double)CCrecipe.get(6)/100*make)/Productionlines) + "ms " +"["+ merchandiseInventory.get(2)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks1");
				}
			}else {
				if(materialsCheckerCCrand((merchandiseCapacity - total)/4)){
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((CCrecipe.get(i)/100)*(merchandiseCapacity - total)/4)));
					}
					merchandiseInventory.set(2, merchandiseInventory.get(2)+(merchandiseCapacity - total)/4);
					total_manufactured = total_manufactured+((merchandiseCapacity - total)/4);
		/*			try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Chocolate Chip Cookeis " + ((merchandiseCapacity - total)/4) + "Kg " + "Processed: " + (((double)CCrecipe.get(6)/100*((merchandiseCapacity - total)/4))/Productionlines) + "ms " +"["+ merchandiseInventory.get(2)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks2");
				}
			}
		}
	}
	
	public void merchandiseCheckerGC(int make) {
		if(total < merchandiseCapacity) {
			if( make <= ((merchandiseCapacity - total)/4) ) {
				if(materialsCheckerGCmake(make)) {
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((GCrecipe.get(i)/100)*make)));
					}
					merchandiseInventory.set(3, merchandiseInventory.get(3)+make);
					total_manufactured = total_manufactured+make;
	/*				try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Ginger Cookies " + make + "Kg " + "Processed: " + (((double)GCrecipe.get(6)/100*make)/Productionlines) + "ms " +"["+ merchandiseInventory.get(3)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks1");
				}
			}else {
				if(materialsCheckerGCrand((merchandiseCapacity - total)/4)){
					for(int i = 0; i < warehouseInventory.size(); i++) {
						warehouseInventory.set(i, (warehouseInventory.get(i)-((GCrecipe.get(i)/100)*(merchandiseCapacity - total)/4)));
					}
					merchandiseInventory.set(3, merchandiseInventory.get(3)+(merchandiseCapacity - total)/4);
					total_manufactured = total_manufactured+((merchandiseCapacity - total)/4);
		/*			try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
						bw.newLine();
						bw.write("Ginger Cookies " + ((merchandiseCapacity - total)/4) + "Kg " + "Processed: " + (((double)GCrecipe.get(6)/100*((merchandiseCapacity - total)/4))/Productionlines) + "ms " +"["+ merchandiseInventory.get(3)+"]");
					} catch(IOException e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("Out of stocks2");
				}
			}
		}
	}
	
	public void warehouseCheckerBB(int Amount) {
		if(materialsCheckerBBmake(Amount)) {
			for(int i = 0; i < warehouseInventory.size(); i++) {
				warehouseInventory.set(i, (warehouseInventory.get(i)-((BBrecipe.get(i)/100)*Amount)));
			}
			merchandiseInventory.set(0, merchandiseInventory.get(0)+Amount);
			total_manufactured = total_manufactured+Amount;
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
				bw.newLine();
				bw.write("Base Biscuits " + Amount + "Kg " + "Processed: " + (((double)BBrecipe.get(6)/100*Amount)/Productionlines) + "ms " +"["+ merchandiseInventory.get(0)+"]");
				bw.newLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Can't produce1");
			state = 0;
		}
	}
	
	public void warehouseCheckerBC(int Amount) {
		if(materialsCheckerBCmake(Amount)) {
			for(int i = 0; i < warehouseInventory.size(); i++) {
				warehouseInventory.set(i, (warehouseInventory.get(i)-((BCrecipe.get(i)/100)*Amount)));
			}
			merchandiseInventory.set(1, merchandiseInventory.get(1)+Amount);
			total_manufactured = total_manufactured+Amount;
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
				bw.newLine();
				bw.write("Butter Cookies " + Amount + "Kg " + "Processed: " + (((double)BCrecipe.get(6)/100*Amount)/Productionlines) + "ms " +"["+ merchandiseInventory.get(1)+"]");
				bw.newLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Can't produce1");
			state = 0;
		}
	}
	
	public void warehouseCheckerCC(int Amount) {
		if(materialsCheckerCCmake(Amount)) {
			for(int i = 0; i < warehouseInventory.size(); i++) {
				warehouseInventory.set(i, (warehouseInventory.get(i)-((CCrecipe.get(i)/100)*Amount)));
			}
			merchandiseInventory.set(2, merchandiseInventory.get(2)+Amount);
			total_manufactured = total_manufactured+Amount;
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
				bw.newLine();
				bw.write("Chocolate Chips Cookies " + Amount + "Kg " + "Processed: " + (((double)CCrecipe.get(6)/100*Amount)/Productionlines) + "ms " +"["+ merchandiseInventory.get(2)+"]");
				bw.newLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Can't produce1");
			state = 0;
		}
	}
	
	public void warehouseCheckerGC(int Amount) {
		if(materialsCheckerGCmake(Amount)) {
			for(int i = 0; i < warehouseInventory.size(); i++) {
				warehouseInventory.set(i, (warehouseInventory.get(i)-((GCrecipe.get(i)/100)*Amount)));
			}
			merchandiseInventory.set(3, merchandiseInventory.get(3)+Amount);
			total_manufactured = total_manufactured+Amount;
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
				bw.newLine();
				bw.write("Ginger Cookies " + Amount + "Kg " + "Processed: " + (((double)GCrecipe.get(6)/100*Amount)/Productionlines) + "ms " +"["+ merchandiseInventory.get(3)+"]");
				bw.newLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Can't produce1");
			state = 0;
		}
	}
	
	public void warehouseChecker() {
		sum = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			sum = sum + warehouseInventory.get(i);
		}
	}
	
	public boolean materialsCheckerBBmake(int make) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((BBrecipe.get(i)/100)*make) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerBCmake(int make) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((BCrecipe.get(i)/100)*make) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerCCmake(int make) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((CCrecipe.get(i)/100)*make) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerGCmake(int make) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((GCrecipe.get(i)/100)*make) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerBBrand(int rand) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((BBrecipe.get(i)/100)*rand) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerBCrand(int rand) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((BCrecipe.get(i)/100)*rand) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerCCrand(int rand) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((CCrecipe.get(i)/100)*rand) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean materialsCheckerGCrand(int rand) {
		int a = 0;
		for(int i = 0; i < warehouseInventory.size(); i++ ) {
			if(((GCrecipe.get(i)/100)*rand) <= warehouseInventory.get(i) ) {
				a++;
			}
		}
		if(a == 6) {
			return true;
		}else {
			return false;
		}
	}
	
}