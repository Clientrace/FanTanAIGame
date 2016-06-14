package Manager;


public class Pekwaman {
	public static String agentName = "Pekwaman";
	public static String grpName = "PEKHUAWEI";
	public static String members = "Angeles, Carl Ian Jonathan B.; Montesa, Mark Angelo F."
								 + "Reveche, Rad Renzo D.; Vergara, Jonathan Elli G.";
	public static int playerNumber = 2; 
	
	
	
	public static void move(){
		int AgentMove = APHA_BETA_SEARCH();
		AgentData.move(playerNumber,AgentMove);
	}
	

	//Algorithm
	public static int APHA_BETA_SEARCH(){
		int ValidCard=0,i, min=0;
		int MaxWeight,MinWeight=100;
		
		Initializer();
		int CardSet[] = AgentData.checkHand(playerNumber);
		for(i=0;i<CardSet.length;i++)
			CardSet[i] = CardSet[i] +1;
		
		SearchMoves(CardSet); 
		MaxWeight = COMPUTE_MAXVALUE(CardSet);
		int numBestMove = getNumberOfBestMove(MaxWeight);

		if(numBestMove>1){
			MinWeight = COMPUTE_MINVALUE(MaxWeight);
			min=1;
			
			for(i=0;i<AgentData.getNumCards(playerNumber);i++){
				for(int j=0;j<13;j++){
					if(MaxValues[j]==MaxWeight && PossibleMove[j]==CardSet[i] && MaxValues[j]!=-1){
						if(min==1){
							if(MinValues[j] == MinWeight){
								ValidCard=i;
								break;
							}
						}
						else if(min==0){
							ValidCard=i;
							break;
						}
					}
				}
			}
		}else if(numBestMove==1){
			for(i=0;i<AgentData.getNumCards(playerNumber);i++)
				for(int j=0;j<13;j++)
					if(CardSet[i] == PossibleMove[j] && MaxValues[j]==MaxWeight){
						ValidCard = i;
						break;
					}
		}
		
		int Yes = VerifyAgentMove(CardSet[ValidCard],CardSet);
		
		if(Yes==0)
			ValidCard = DoubleCheck(CardSet);

		//if six diamond
		for(i=0;i<CardSet.length;i++)
			if(CardSet[i] == 45)
				ValidCard = i;
		
		return ValidCard;
	}
	
	public static void Initializer(){
		for(int count=0; count< 13; count++){
			if(count<8)
				DataSet[count]=-1;
			
			if(count<13){
				PossibleMove[count]=-1;
				MaxValues[count]=-1;
				MinValues[count]=100;
			}
		}
	}
	
	public static void SearchMoves(int CardSet[]){
		int count=0, i,j;
		DataSet[0] = AgentData.getCardTop(0);
		DataSet[1] = AgentData.getCardTop(1);
		DataSet[2] = AgentData.getCardTop(2);
		DataSet[3] = AgentData.getCardTop(3);
		DataSet[4] = AgentData.getCardBottom(0);
		DataSet[5] = AgentData.getCardBottom(1);
		DataSet[6] = AgentData.getCardBottom(2);
		DataSet[7] = AgentData.getCardBottom(3);
		
		
		for(i=0; i< CardSet.length ; i++){
			for(j=0;j<8;j++){
				if(CardSet[i] == DataSet[j]){
					int insert = CheckIfIncluded(DataSet[j]);
				
					if(insert==0){
						PossibleMove[count]=DataSet[j];
						count++;
					}
				}
			}
		}
	}
	
	public static int CheckIfIncluded(int Temp){
		int Ret=0, k;		
		for(k=0;k<13;k++)
			if(PossibleMove[k]==Temp)
				Ret=1;
		
		return Ret;
	}
	
	public static int COMPUTE_MAXVALUE(int Set[]){
		
		int LowerBound=0, UpperBound=0, Val=-1, count=0, Type=0;
		for(count =0; count<13; count++){
			Val=0;
			
			if(PossibleMove[count]!= -1 && PossibleMove[count] <= 13){
				LowerBound=1;
				UpperBound=13;
				Type= 0;
				Val = WeighEvaluation(PossibleMove[count],Set,LowerBound,UpperBound,Type);
			
			}else if(PossibleMove[count]!=-1 && PossibleMove[count] <=26){
				LowerBound=14;
				UpperBound=26;
				Type= 1;
				Val = WeighEvaluation(PossibleMove[count],Set,LowerBound,UpperBound,Type);
			 
			}else if(PossibleMove[count]!=-1 && PossibleMove[count] <=39){
				LowerBound=27;
				UpperBound=39;
				Type= 2;
				Val = WeighEvaluation(PossibleMove[count],Set,LowerBound,UpperBound,Type);
			
			}else if(PossibleMove[count]!=-1 && PossibleMove[count] <=52){
				LowerBound=40;
				UpperBound=52;
				Type= 3;
				Val = WeighEvaluation(PossibleMove[count],Set,LowerBound,UpperBound, Type);
			}
			
			if(PossibleMove[count]== -1)
				Val=-2;
			
			MaxValues[count] = Val;  
		}
		
		int HighestWeight=0;
		for(int i=0;i<13;i++){
			if(MaxValues[i]>HighestWeight)
				HighestWeight=MaxValues[i];
		}
		
		return HighestWeight;
	}
	
	public static int WeighEvaluation(int Move, int Set[], int LowerBound, int UpperBound, int Type){
		int Temp=0, Temp2=0;
		
		
		for(int j=0; j<AgentData.getNumCards(playerNumber); j++){
			if(Set[j] != Move && Set[j] <= UpperBound && Set[j] >= LowerBound && Set[j]!=0){
				if(Move%13==0){
					Temp2 = 13 - AgentData.getCardBottom(Type)%13;
				}else if(Move%13 <6){
					if(Set[j]%13 == 0 && Move%13 >=6){
						Temp2 = 13 - AgentData.getCardBottom(Type)%13;
					}else if(Set[j]%13 <6){
						Temp2 = AgentData.getCardTop(Type)%13 - Set[j]%13;
					}
				}else if(Move%13 ==6){
					if(Set[j]%13 == 0){
						Temp2 = Math.abs(AgentData.getCardTop(Type)%13 - 13);
					}else{ 
						Temp2 = Math.abs(AgentData.getCardTop(Type)%13 - Set[j]%13);
					}					
				}else{
					if(Set[j]%13 == 0){
						Temp2 = 13 - AgentData.getCardBottom(Type)%13;
					}
					else if(Set[j]%13 >6){
						Temp2 = Set[j]%13 - AgentData.getCardBottom(Type)%13;
					}
				}
							
				Temp = Temp + Temp2;
			}
		}
		return Temp;
	}
	
	public static int getNumberOfBestMove(int MaxWeight){
		int num=0;
		for(int i=0;i<13;i++)
			if(MaxWeight==MaxValues[i] && MaxValues[i]!=-1)
				num++;
		
		return num;
	}

	public static int COMPUTE_MINVALUE(int Temp){
		int i;
		
		for(i=0;i<13;i++){
			if(MaxValues[i]==Temp){
				EvaluateMinValue(i);
			}
		}
		
		int Temp2=100;
		for(i=0;i<13;i++){
			if(MinValues[i] < Temp2 && MinValues[i]!=100){
				Temp2 = MinValues[i];
			}
		}
		
		return Temp2;
	}
	
	public static void EvaluateMinValue(int Index){
		if(PossibleMove[Index] <= 13){
			if(PossibleMove[Index]%13 == 0){
				MinValues[Index] = 0; 
			}else if(PossibleMove[Index]%13 < 6){
				MinValues[Index] = AgentData.getCardTop(0)%13 - 1;
			}else
				MinValues[Index] = 13 - AgentData.getCardTop(0)%13;
		}else if(PossibleMove[Index] <= 26){
			if(PossibleMove[Index]%13 == 0){
				MinValues[Index] = 0; 
			}else if(PossibleMove[Index]%13 < 6){
				MinValues[Index] = AgentData.getCardTop(1)%13 - 1;
			}else
				MinValues[Index] = 13 - AgentData.getCardTop(1)%13;
		}else if(PossibleMove[Index] <= 39){
			if(PossibleMove[Index]%13 == 0){
				MinValues[Index] = 0; 
			}else if(PossibleMove[Index]%13 < 6){
				MinValues[Index] = AgentData.getCardTop(2)%13 - 1;
			}else
				MinValues[Index] = 13 - AgentData.getCardTop(2)%13;
		}else{
			if(PossibleMove[Index]%13 == 0){
				MinValues[Index] = 0; 
			}else if(PossibleMove[Index]%13 < 6){
				MinValues[Index] = AgentData.getCardTop(3)%13 - 1;
			}else
				MinValues[Index] = 13 - AgentData.getCardTop(3)%13;
		}
	}
	
	public static int DoubleCheck(int CardSet[]){
		int i, Index=-1;
		for(i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(PossibleMove[0]==CardSet[i]){
				Index=i;
				break;
			}
		}
		return Index;
	}

	public static int VerifyAgentMove(int Move, int CardSet[]){
		int Yes=0;
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(CardSet[i]==Move){
				Yes=1;
				break;
			}
		}
		return Yes;
	}
	
	static int PossibleMove[] = new int[13];
	static int MaxValues[] = new int[13];
	static int MinValues[] = new int[13];
	static int DataSet[] = new int[8];
}