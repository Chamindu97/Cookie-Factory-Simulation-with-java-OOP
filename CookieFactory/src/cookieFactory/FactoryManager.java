package cookieFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FactoryManager extends FactoryRunner{
	
  	public void updateInfo() {
		int i;
		int count=0;
		for(i=2; i<6; i++ ) {
			char ROW[] = cookieFactory.get(i).toCharArray();
				if(ROW[count] == 'P' || ROW[count] == 'p' ) {
					Productionlines = Character.getNumericValue(ROW[count+17]);
				}
				if(ROW[count] == 'W' || ROW[count] == 'w') {
					if(Character.isSpaceChar(ROW[count+31])){
						warehouseCapacity = (Character.getNumericValue(ROW[count+28]))*100 + (Character.getNumericValue(ROW[count+29]))*10 + (Character.getNumericValue(ROW[count+30]));
					}else{
						warehouseCapacity = (Character.getNumericValue(ROW[count+28]))*1000 + (Character.getNumericValue(ROW[count+29]))*100 + (Character.getNumericValue(ROW[count+30]))*10 + (Character.getNumericValue(ROW[count+31]));
					}
				}
				if(ROW[count] == 'M' || ROW[count] == 'm' || ROW[count] == 'F' || ROW[count] == 'f') {
					if(Character.isSpaceChar(ROW[count+29])){
						merchandiseCapacity = (Character.getNumericValue(ROW[count+26]))*100 + (Character.getNumericValue(ROW[count+27]))*10 + (Character.getNumericValue(ROW[count+28]));
					}else{
						merchandiseCapacity = (Character.getNumericValue(ROW[count+26]))*1000 + (Character.getNumericValue(ROW[count+27]))*100 + (Character.getNumericValue(ROW[count+28]))*10 + (Character.getNumericValue(ROW[count+29]));
					}
				}
				if(ROW[count] == 'R' || ROW[count] == 'r') {
					if(Character.isDigit(ROW[count+12])){
						Runtime = (Character.getNumericValue(ROW[count+9]))*1000 + (Character.getNumericValue(ROW[count+10]))*100 + (Character.getNumericValue(ROW[count+11]))*10 + (Character.getNumericValue(ROW[count+12]));
					}else{
						Runtime = (Character.getNumericValue(ROW[count+9]))*100 + (Character.getNumericValue(ROW[count+10]))*10 + (Character.getNumericValue(ROW[count+11]));
					}
				}
		}

		for(i=8; i<12; i++) {
			char ROW[] = cookieFactory.get(i).toCharArray();
			switch(ROW[count]) {
				case 'B' :
					if (ROW[count+1] == 'B') {
						BB(i);
						break;
					}else{
						BC(i);
						break;
					}
				case 'C' :
						CC(i);
						break;
				case 'G' :	
						GC(i);
						break;
			}
		}
		
		int FL = 0, BT = 0, CH = 0, MK = 0, SG = 0, GN = 0;
		count = 0;
		for(i=14; i<20; i++) {
			char ROW[] = cookieFactory.get(i).toCharArray();
			switch(ROW[count]) {
				case 'F' :
					if(Character.isSpaceChar(ROW[count+5])){
						FL = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
					}else{
						FL = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
					}
					break;
				case 'B' :
					if(Character.isSpaceChar(ROW[count+5])){
						BT = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));;
					}else{
						BT = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
					}
					break;
				case 'C' :
					if(Character.isSpaceChar(ROW[count+5])){
						CH = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
					}else{
						CH = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
					}
					break;
				case 'M' :
					if(Character.isSpaceChar(ROW[count+5])){
						MK = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
					}else{
						MK = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
					}
					break;
				case 'S' :
					if(Character.isSpaceChar(ROW[count+5])){
						SG = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
					}else {
						SG = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));	
					}
					break;
				case 'G' :
					if(ROW[count+1] == 'N') {
						if(Character.isSpaceChar(ROW[count+5])){
							GN = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
						}else{
							GN = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));	
						}		
					}
					break;
			}
		}
		warehouseInventory.add(0, FL);
		warehouseInventory.add(1, BT);
		warehouseInventory.add(2, CH);
		warehouseInventory.add(3, MK);
		warehouseInventory.add(4, SG);
		warehouseInventory.add(5, GN);
		
		
		int BB = 0, BC = 0, CC = 0, GC = 0;
		count =0;
		for(i=22; i<26; i++) {
			char ROW[] = cookieFactory.get(i).toCharArray();
			switch(ROW[count]) {
				case 'B' :
					if(ROW[count+1] == 'B') {
						if(Character.isSpaceChar(ROW[count+5])){
							BB = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
						}else{
							BB = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
						}
						break;
					}else{
						if(Character.isSpaceChar(ROW[count+5])){
							BC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
						}else{
							BC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
						}
						break;
					}
				case 'C' :
					if(Character.isSpaceChar(ROW[count+5])){
						CC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
					}else{
						CC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
					}
					break;
				case 'G' :
					if((count+5) < ROW.length ){
						if(Character.isSpaceChar(ROW[count+5])){
							GC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
						}else{
							GC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
						}
						break;
					}else{
						GC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
					}
					break;
			}
		}
		merchandiseInventory.add(0, BB);
		merchandiseInventory.add(1, BC);
		merchandiseInventory.add(2, CC);
		merchandiseInventory.add(3, GC);
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~INFO file updated~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public static void BB(int i) {                                                    // initialize the recipe to an ArrayList
		int check, FL = 0, BT=0, CH=0, MK=0, SG=0, GN=0 ,time=0;
		char ROW[] = cookieFactory.get(i).toCharArray();
		for (check = 4; check<ROW.length;check++) {
			switch (ROW[check]) {
				case 'F' :
					FL = Character.getNumericValue(ROW[check+3]);
					FL = (FL*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'B' :
					BT = Character.getNumericValue(ROW[check+3]);
					BT = (BT*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'C' :
					CH = Character.getNumericValue(ROW[check+3]);
					CH = (CH*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'M' :
					MK = Character.getNumericValue(ROW[check+3]);
					MK = (MK*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'S' :
					SG = Character.getNumericValue(ROW[check+3]);
					SG = (SG*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'G' :
					if(ROW[check+1] == 'N' ) {
					GN = Character.getNumericValue(ROW[check+3]);
					GN = (GN*10) + Character.getNumericValue(ROW[check+4]);
					}
					break;
				case '|' :
					time = Character.getNumericValue(ROW[check+2]);
					time = (time*10) + Character.getNumericValue(ROW[check+3]);
					break;
					
			}
		}
		BBrecipe.add(0, FL);
		BBrecipe.add(1, BT);
		BBrecipe.add(2, CH);
		BBrecipe.add(3, MK);
		BBrecipe.add(4, SG);
		BBrecipe.add(5, GN);
		BBrecipe.add(6, time);
	}
	
	public static void BC(int i) {
		int check, FL = 0, BT=0, CH=0, MK=0, SG=0, GN=0 ,time=0;
		char ROW[] = cookieFactory.get(i).toCharArray();
		for (check = 4; check<ROW.length;check++) {
			switch (ROW[check]) {
				case 'F' :
					FL = Character.getNumericValue(ROW[check+3]);
					FL = (FL*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'B' :
					BT = Character.getNumericValue(ROW[check+3]);
					BT = (BT*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'C' :
					CH = Character.getNumericValue(ROW[check+3]);
					CH = (CH*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'M' :
					MK = Character.getNumericValue(ROW[check+3]);
					MK = (MK*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'S' :
					SG = Character.getNumericValue(ROW[check+3]);
					SG = (SG*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'G' :
					if(ROW[check+1] == 'N' ) {
					GN = Character.getNumericValue(ROW[check+3]);
					GN = (GN*10) + Character.getNumericValue(ROW[check+4]);
					}
					break;
				case '|' :
					time = Character.getNumericValue(ROW[check+2]);
					time = (time*10) + Character.getNumericValue(ROW[check+3]);
					break;
					
			}
		}
		BCrecipe.add(0, FL);
		BCrecipe.add(1, BT);
		BCrecipe.add(2, CH);
		BCrecipe.add(3, MK);
		BCrecipe.add(4, SG);
		BCrecipe.add(5, GN);
		BCrecipe.add(6, time);
	}

	public static void CC(int i) {
		int check, FL = 0, BT=0, CH=0, MK=0, SG=0, GN=0 ,time=0;
		char ROW[] = cookieFactory.get(i).toCharArray();
		for (check = 4; check<ROW.length;check++) {
			switch (ROW[check]) {
				case 'F' :
					FL = Character.getNumericValue(ROW[check+3]);
					FL = (FL*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'B' :
					BT = Character.getNumericValue(ROW[check+3]);
					BT = (BT*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'C' :
					CH = Character.getNumericValue(ROW[check+3]);
					CH = (CH*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'M' :
					MK = Character.getNumericValue(ROW[check+3]);
					MK = (MK*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'S' :
					SG = Character.getNumericValue(ROW[check+3]);
					SG = (SG*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'G' :
					if(ROW[check+1] == 'N' ) {
					GN = Character.getNumericValue(ROW[check+3]);
					GN = (GN*10) + Character.getNumericValue(ROW[check+4]);
					}
					break;
				case '|' :
					time = Character.getNumericValue(ROW[check+2]);
					time = (time*10) + Character.getNumericValue(ROW[check+3]);
					break;
					
			}
		}
		CCrecipe.add(0, FL);
		CCrecipe.add(1, BT);
		CCrecipe.add(2, CH);
		CCrecipe.add(3, MK);
		CCrecipe.add(4, SG);
		CCrecipe.add(5, GN);
		CCrecipe.add(6, time);
	}

	public static void GC(int i) {
		int check, FL = 0, BT=0, CH=0, MK=0, SG=0, GN=0 ,time=0;
		char ROW[] = cookieFactory.get(i).toCharArray();
		for (check = 4; check<ROW.length;check++) {
			switch (ROW[check]) {
				case 'F' :
					FL = Character.getNumericValue(ROW[check+3]);
					FL = (FL*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'B' :
					BT = Character.getNumericValue(ROW[check+3]);
					BT = (BT*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'C' :
					CH = Character.getNumericValue(ROW[check+3]);
					CH = (CH*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'M' :
					MK = Character.getNumericValue(ROW[check+3]);
					MK = (MK*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'S' :
					SG = Character.getNumericValue(ROW[check+3]);
					SG = (SG*10) + Character.getNumericValue(ROW[check+4]);
					break;
				case 'G' :
					if(ROW[check+1] == 'N' ) {
					GN = Character.getNumericValue(ROW[check+3]);
					GN = (GN*10) + Character.getNumericValue(ROW[check+4]);
					}
					break;
				case '|' :
					time = Character.getNumericValue(ROW[check+2]);
					time = (time*10) + Character.getNumericValue(ROW[check+3]);
					break;
					
			}
		}
		GCrecipe.add(0, FL);
		GCrecipe.add(1, BT);
		GCrecipe.add(2, CH);
		GCrecipe.add(3, MK);
		GCrecipe.add(4, SG);
		GCrecipe.add(5, GN);
		GCrecipe.add(6, time);
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~RECIPES updated~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void updateInvoice1(int i) {												//Should pass the i value to get the Array line of the list
		int BB = 0, BC = 0, CC = 0, GC = 0, time = 0;
		int count; 
			char ROW[] = invoice.get(i).toCharArray();
			for(count=0; count<ROW.length;count++) {
				switch(ROW[count]) {
					case 'B' :
						switch(ROW[count+1]) {
							case 'B' :
								if(Character.isDigit(ROW[count+5])) {
									BB = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
								}else {
									BB = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
								}
								break;
							case  'C' :
								if(Character.isDigit(ROW[count+5])) {
									BC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
								}else {
									BC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
								}
								break;
						}
						break;
					case 'C' :
						if(ROW[count+1] == 'C') {
							if(Character.isDigit(ROW[count+5])) {
								CC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								CC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}
						break;
					case 'G' :
						if(ROW[count+1] == 'C') {
							if(Character.isDigit(ROW[count+5])) {
								GC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								GC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}
						break;
				}
				time = (Character.getNumericValue(ROW[6]))*100 + (Character.getNumericValue(ROW[7]))*10 + (Character.getNumericValue(ROW[8]));
			}
			
			if( i!= 0 ) {
				char ROW2[] = invoice.get(invoice_num).toCharArray();
				tempTime = (Character.getNumericValue(ROW2[6]))*100 + (Character.getNumericValue(ROW2[7]))*10 + (Character.getNumericValue(ROW2[8]));
			}
			
		tempInvoice.add(0, BB);
		tempInvoice.add(1, BC);
		tempInvoice.add(2, CC);
		tempInvoice.add(3, GC);
		tempInvoice.add(4, time);
		for(int x = 0; x < 4; x++) {
			total_requirment = total_requirment + tempInvoice.get(x);
		}
	}
	
	public void updateInvoice2(int i) {												//Should pass the i value to get the Array line of the list
		int BB = 0, BC = 0, CC = 0, GC = 0, time = 0;
		int count; 
			char ROW[] = invoice.get(i).toCharArray();
			for(count=0; count<ROW.length;count++) {
				switch(ROW[count]) {
					case 'B' :
						switch(ROW[count+1]) {
							case 'B' :
								if(Character.isDigit(ROW[count+5])) {
									BB = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
								}else {
									BB = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
								}
								break;
							case  'C' :
								if(Character.isDigit(ROW[count+5])) {
									BC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
								}else {
									BC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
								}
								break;
						}
						break;
					case 'C' :
						if(ROW[count+1] == 'C') {
							if(Character.isDigit(ROW[count+5])) {
								CC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								CC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}
						break;
					case 'G' :
						if(ROW[count+1] == 'C') {
							if(Character.isDigit(ROW[count+5])) {
								GC = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								GC = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}
						break;
				}
				time = (Character.getNumericValue(ROW[6]))*100 + (Character.getNumericValue(ROW[7]))*10 + (Character.getNumericValue(ROW[8]));
			}
			
			if( i!= 0 ) {
				char ROW2[] = invoice.get(invoice_num).toCharArray();
				tempTime = (Character.getNumericValue(ROW2[6]))*100 + (Character.getNumericValue(ROW2[7]))*10 + (Character.getNumericValue(ROW2[8]));
			}
			
		tempInvoice.set(0, BB);
		tempInvoice.set(1, BC);
		tempInvoice.set(2, CC);
		tempInvoice.set(3, GC);
		tempInvoice.set(4, time);
		for(int x = 0; x < 4; x++) {
			total_requirment = total_requirment + tempInvoice.get(x);
		}
	}
	
	public void updateArrival1(int i) {												//Should pass the i value to get the Array line of the list
		int FL = 0, BT = 0, CH = 0, MK = 0, SG = 0, GN = 0, time = 0;
		int count; 
			char ROW[] = rmArrival.get(i).toCharArray();
			for(count=0; count<ROW.length;count++) {
				switch(ROW[count]) {
					case 'F' :
						if(ROW[count+1] == 'L') {
							if(Character.isDigit(ROW[count+5])) {
								FL = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								FL = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'B' :
						if(ROW[count+1] == 'T') {
							if(Character.isDigit(ROW[count+5])) {
								BT = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								BT = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'C' :
						if(ROW[count+1] == 'H') {
							if(Character.isDigit(ROW[count+5])) {
								CH = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								CH = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'M' :
						if(ROW[count+1] == 'K') {
							if(Character.isDigit(ROW[count+5])) {
								MK = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								MK = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'S' :
						if(ROW[count+1] == 'G') {
							if(Character.isDigit(ROW[count+5])) {
								SG = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								SG = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'G' :
						if(ROW[count+1] == 'N') {
							if(Character.isDigit(ROW[count+5])) {
								GN = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								GN = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
				}
				if(Character.isSpaceChar(ROW[4])){
					time = (Character.getNumericValue(ROW[5]))*100 + (Character.getNumericValue(ROW[6]))*10 + (Character.getNumericValue(ROW[7]));
				}
				if(Character.isSpaceChar(ROW[5])){
					time = (Character.getNumericValue(ROW[6]))*100 + (Character.getNumericValue(ROW[7]))*10 + (Character.getNumericValue(ROW[8]));
				}else {
					time = (Character.getNumericValue(ROW[7]))*100 + (Character.getNumericValue(ROW[8]))*10 + (Character.getNumericValue(ROW[9]));
				}
			}
		
		tempArrival.add(0, FL);
		tempArrival.add(1, BT);
		tempArrival.add(2, CH);
		tempArrival.add(3, MK);
		tempArrival.add(4, SG);
		tempArrival.add(5, GN);
		tempArrival.add(6, time);
		
	}
	
	public void updateArrival2(int i) {												//Should pass the i value to get the Array line of the list
		int FL = 0, BT = 0, CH = 0, MK = 0, SG = 0, GN = 0, time = 0;
		int count; 
			char ROW[] = rmArrival.get(i).toCharArray();
			for(count=0; count<ROW.length;count++) {
				switch(ROW[count]) {
					case 'F' :
						if(ROW[count+1] == 'L') {
							if(Character.isDigit(ROW[count+5])) {
								FL = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								FL = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'B' :
						if(ROW[count+1] == 'T') {
							if(Character.isDigit(ROW[count+5])) {
								BT = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								BT = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'C' :
						if(ROW[count+1] == 'H') {
							if(Character.isDigit(ROW[count+5])) {
								CH = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								CH = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'M' :
						if(ROW[count+1] == 'K') {
							if(Character.isDigit(ROW[count+5])) {
								MK = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								MK = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'S' :
						if(ROW[count+1] == 'G') {
							if(Character.isDigit(ROW[count+5])) {
								SG = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								SG = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
					case 'G' :
						if(ROW[count+1] == 'N') {
							if(Character.isDigit(ROW[count+5])) {
								GN = (Character.getNumericValue(ROW[count+3]))*100 + (Character.getNumericValue(ROW[count+4]))*10 + (Character.getNumericValue(ROW[count+5]));
							}else {
								GN = (Character.getNumericValue(ROW[count+3]))*10 + (Character.getNumericValue(ROW[count+4]));
							}
						}break;
				}
				if(Character.isSpaceChar(ROW[4])){
					time = (Character.getNumericValue(ROW[5]))*100 + (Character.getNumericValue(ROW[6]))*10 + (Character.getNumericValue(ROW[7]));
				}
				if(Character.isSpaceChar(ROW[5])){
					time = (Character.getNumericValue(ROW[6]))*100 + (Character.getNumericValue(ROW[7]))*10 + (Character.getNumericValue(ROW[8]));
				}else {
					time = (Character.getNumericValue(ROW[7]))*100 + (Character.getNumericValue(ROW[8]))*10 + (Character.getNumericValue(ROW[9]));
				}
			}
		
		tempArrival.set(0, FL);
		tempArrival.set(1, BT);
		tempArrival.set(2, CH);
		tempArrival.set(3, MK);
		tempArrival.set(4, SG);
		tempArrival.set(5, GN);
		tempArrival.set(6, time);
		
	}
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DATA updated~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	
	public void completeInvoice(int j) {
		int i;
		System.out.println(merchandiseInventory);
		for(i=0; i<4; i++) {
			merchandiseInventory.set(i, (merchandiseInventory.get(i) - tempInvoice.get(i)));
			total_dispatched = total_dispatched+tempInvoice.get(i);
		}
		System.out.println("invoice " +j +" completed");
		System.out.println(" \n \n");
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true))){
			bw.newLine();
			bw.write("Invoice " +invoice.get(j));
			bw.newLine();
			bw.write("Completed " +tempInvoice.get(4));
			bw.newLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		invoice_num = j;
		total_invoices++;
	}
}