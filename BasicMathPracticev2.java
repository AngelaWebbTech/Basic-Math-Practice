//PURPOSE OF PROGRAM------> practice basic math (+ - x %)


//10.25.22 PROBLEM: custom setup needs validations and user warnings - currently accepts unusable data
//10.25.22 ADD FEATURE SOON: add a "grand total" to final data display - one set of data that shows one overall score for all rounds combined
//10.19.22 ADD FEATURE SOON: at end of game, question exists to keep custom settings for challengeLevel=5. Need this question for all levels. Also need to add ability to replay 
			//the same settings instead of reentering mathType and challengeLevel=5
//10.19.22 ADD FEATURE LATER: customSettings array for each user. will need an option to erase it. max number of users or warning when user hasn't been used for a while - for the 
			//purpose of freeing up memory?
//10.19.22 ADD FEATURE SOON: in custom level setup, maximum numbers are allowed to be smaller than the minimum numbers (for digits chosen) - add warning that all answers will be negative

// 7.1.22 ADD FEATURE LATER: timer starts/stops needs to be added (make this a function that is called in each section if needed)
//7.20.22 ADD FEATURE LATER: (fileWriter/printWriter) need to add an option to save the custom level setup

//7.24.22 NOTE: timer does not determine points - timer keeps track of time as a measure of speed, points are added same as no-timer method in all levels




import java.util.Random; //random number generator
import java.util.ArrayList; //storing data for each round
import java.util.Scanner; //reading answer inputs for division
import java.io.FileWriter; //filewriter for high scores file
import java.io.*; //FileReader in case(8)
import javax.swing.*; //scroll pane @ end of game

public class BasicMathPracticev2 {//2354

	public static void main(String[] args) {//2072
		
		//--------------------------------------------------------------------------------------------------------------------------------------
		//-------------------                            GLOBAL VARIABLES                                                     ------------------
		//--------------------------------------------------------------------------------------------------------------------------------------
		
		double correctAnswerDbl=0, correctAnswerDivPart2Dbl, finalScore, firstDigitDbl, secondDigitDbl, userGuessDbl=0, userGuessRemainder=0.0;
		
		int chooseAddorSub, chooseAddSubMultDiv,chooseMultorDiv, correctAnswer, correctAnswerDivPart1=0, correctAnswerDivPart2=0, correctAnswerScore=0, firstDigit=0, 
				max1stDigit=0, max2ndDigit=0, min1stDigit=0, min2ndDigit=0, numOfProblems=0, pointTotal=0, remainder, remainderNumberAnswer=0, 
				roundCount=1, roundNumDisplayCounter=1, scoreOutputCounter, secondDigit=0, totalCorrectAnswers=0, totalProblems=0, userGuess, userGuessPart1=0, 
				wholeNumberAnswer=0, wrongAnswerScore=0;
		
		String allowRemainders="", carryBorrow="", challengeLevel="", chooseAddorSubStr, chooseAddSubMultDivStr, chooseMultorDivStr, correctAnswerScoreInput="", mathType="", 
				negativeAnswer="", numOfProblemsInput="", playAgain, remainderEntered="", remainderInput, scoreOutput="", userGuessInput="", 
				username, wholeNumbersEntered="", wholeOrDecimal="", wrongAnswerScoreInput="", line, saveCustomSettings, minMaxInput;
		
		boolean noBorrowing, noCarrying, noNegatives, noRemainders;
		
		
		//--------------------------------------------------------------------------------------------------------------------------------------
		//-------------------                            STORAGE SETUP (arrays, arraylists)                                   ------------------
		//--------------------------------------------------------------------------------------------------------------------------------------		
		
		//ArrayList for pointTotal, totalCorrectAnswers, totalProblems
		java.util.List<Integer> scoreRecord = new ArrayList<Integer>();
		
		//Arrays for temp storage of Custom Settings (challengeLevel = 5)
		String[] customSettingsArrayStr = new String[6];
		int[] customSettingsArrayInt = new int[6];  //to be used later when custom settings save is set up
		

        //--------------------------------------------------------------------------------------------------------------------------------------
        //--------------------                                   WELCOME & SETUP                                            --------------------
        //--------------------------------------------------------------------------------------------------------------------------------------
		
		
		//welcome & prompt for username
		username = JOptionPane.showInputDialog("Hello!\n"
											+ "Welcome to Basic Math Practice!\n\n"
											+ "What's your name?");
		
		//assign nonspecific name if user does not enter one
		if (username==null) {//67
			username = "Person of Mystery";
			playAgain = "y";
			mathType = "not entered yet";
		}//63
		else if (username.length()>0) {//71
			playAgain = "y";
			mathType = "not entered yet";
		}//68
		else {//75
			mathType="exit the game";
			playAgain = "n";
		}//72
		
		
		//--------------------------------------------------------------------------------------------------------------------------------------
		//------                                            CHOOSE TYPE OF PRACTICE                                          -------------------
		//--------------------------------------------------------------------------------------------------------------------------------------
			
	
		while (playAgain.equalsIgnoreCase("Y") && (!mathType.equalsIgnoreCase("exit the game"))) {//2033
			//prompt for type of practice: addition, subtraction, multiplication, division, add & subtract, mult & divide, or all
			mathType = JOptionPane.showInputDialog("Hello " + username + "!\n\n"
														+ "What would you like to do today?\nEnter a number:\n\n"
														+ "1. addition\n"
														+ "2. subtraction\n"
														+ "3. multiplication\n"
														+ "4. division\n"
														+ "5. both addition & subtraction\n"
														+ "6. both multiplication & division\n"
														+ "7. all\n"
														+ "8. see high scores\n"
														+ "9. exit the game" );
			
			//if mathType is not valid, prompt again until a valid choice is made
			if (!mathType.matches("[1-9]")) {//113
				do {//111
					mathType = JOptionPane.showInputDialog(null, "Oops! That option is not available.\n\nPlease choose a number 1-9:\n\n"
						+ "What would you like to do today?\n\n"
						+ "1. addition\n"
						+ "2. subtraction\n"
						+ "3. multiplication\n"
						+ "4. division\n"
						+ "5. both addition & subtraction\n"
						+ "6. both multiplication & division\n"
						+ "7. all\n"
						+ "8. see high scores\n"
						+ "9. exit the game","Oops!",JOptionPane.INFORMATION_MESSAGE);
				}//99
				while (!mathType.matches("[1-9]"));
			}//98
			
			//change mathType to understandable variables
			if (mathType.equals("1"))
				mathType = "addition";
			else if (mathType.equals("2"))
				mathType = "subtraction";
			else if (mathType.equals("3"))
				mathType = "multiplication";
			else if (mathType.equals("4"))
				mathType = "division";
			else if (mathType.equals("5"))
				mathType = "both addition and subtraction";
			else if (mathType.equals("6"))
				mathType = "both multiplication and division";
			else if (mathType.equals("7"))
				mathType = "all";
			else if (mathType.equals("8"))
				mathType = "see high scores";
			else if (mathType.equals("9") || mathType==null)
				System.exit(0);
			else 
				JOptionPane.showMessageDialog(null, "Something has gone wrong with the math type.\nPlease restart the game.", "PROGRAM ERROR", JOptionPane.ERROR_MESSAGE);
			
			
			//-------------------------------------------------------------------------------------------------------------------------------------
			//------                                               CHOOSE CHALLENGE LEVEL                                   -----------------------
			//-------------------------------------------------------------------------------------------------------------------------------------
				
			
			//prompt for challenge level
			challengeLevel = JOptionPane.showInputDialog("Excellent choice!\n\n"
														+ "How challenging do you want the problems to be?\n(choose a number)\n\n"
														+ "1. Beginner:\n\tAdd/Sub:single digits only (answers may be 2 digits)\n\tMult: 1,2,5,10,11 sets only"
														+ 		"\n\tDiv:multiplication table, no remainders\n"
														+ "2. Easy:\n\tAdd/Sub:only double digits, no carrying/borrowing\n\tMult:full standard 12x12 table"
														+ 		"\n\tDiv:2 digits divided by 1 digit, no remainders\n"
														+ "3. Medium:\n\tAdd/Sub:1-2 digit numbers, with carrying/borrowing possible\n\tMult:1-2 digit numbers"
														+ 		"\n\tDiv: 2-3 digits divided by 1-2 digits, with remainders\n"
														+ "4. Hard:\n\tAdd/Sub2-4 digit numbers, with carrying/borrowing & negative answers possible"
														+ 		"\n\tMult:2-4 digit numbers\n\tDiv:1-4 digits for each number, with remainders (written as decimals)\n"
														+ "5. Custom: choose your own conditions\n");
			
			//if challengeLevel is not valid, prompt again until a valid choice is made
				while (!challengeLevel.equals("1") && !challengeLevel.equals("2") && !challengeLevel.equals("3") && !challengeLevel.equals("4") && !challengeLevel.equals("5")) {//169
					challengeLevel = JOptionPane.showInputDialog(null, "Oops! That option is not available.\n\nChoose a number 1-5:\n\n"
							+ "1. Beginner:\n\tAdd/Sub:single digits only (answers may be 2 digits)\n\tMult: 1,2,5,10,11 sets only"
							+ 		"\n\tDiv:multiplication table, no remainders\n"
							+ "2. Easy:\n\tAdd/Sub:only double digits, no carrying/borrowing\n\tMult:full standard 12x12 table"
							+ 		"\n\tDiv:2 digits divided by 1 digit, no remainders\n"
							+ "3. Medium:\n\tAdd/Sub:1-2 digit numbers, with carrying/borrowing possible\n\tMult:1-2 digit numbers"
							+ 		"\n\tDiv: 2-3 digits divided by 1-2 digits, with remainders\n"
							+ "4. Hard:\n\tAdd/Sub2-4 digit numbers, with carrying/borrowing & negative answers possible"
							+ 		"\n\tMult:2-4 digit numbers\n\tDiv:1-4 digits for each number, with remainders (written as decimals)\n"
							+ "5. Custom: choose your own conditions\n","Oops!",JOptionPane.INFORMATION_MESSAGE);
				}//158
	
			//change challengeLevel to understandable variables
			if (challengeLevel.equalsIgnoreCase("1"))
				challengeLevel = "beginner";
			else if (challengeLevel.equals("2"))
				challengeLevel = "easy";
			else if (challengeLevel.equals("3"))
				challengeLevel = "medium";
			else if (challengeLevel.equals("4"))
				challengeLevel = "hard";
			else if (challengeLevel.equals("5"))
				challengeLevel = "custom";
			else { 
				challengeLevel = "";
				JOptionPane.showMessageDialog(null, "Something has gone wrong with setting the challenge level.\nPlease restart the game.", "PROGRAM ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			///////                         Input for Challenge Level 5: Custom                       //////////
			////////////////////////////////////////////////////////////////////////////////////////////////////
				
			
			if (challengeLevel.equals("custom")) {//323
				
				//if mathType=1,2,5, or 7: prompt user: do you want to include problems with carrying & borrowing?
				if (mathType.equals("addition") || mathType.equals("subtraction") || mathType.equals("both addition and subtraction") || mathType.equals("all"))
					while ((!carryBorrow.equalsIgnoreCase("y")) && (!carryBorrow.equalsIgnoreCase("n"))) {//200
						carryBorrow = JOptionPane.showInputDialog("Do you want to include problems that require carrying or borrowing?\n\n(Y)es or (N)o");
					}//198
				
				//if mathType=4, 6, or 7: prompt user: do you want to include problems with remainders?
				if (mathType.equals("division") || mathType.equals("both multiplication and division") || mathType.equals("all"))
					while ((!allowRemainders.equalsIgnoreCase("y")) && (!allowRemainders.equalsIgnoreCase("n"))) {//206
						allowRemainders = JOptionPane.showInputDialog("Do you want to include problems with remainders?\n\n(Y)es or (N)o");
					}//204
				
				//if allowRemainders is yes, should the remainders be expressed in whole numbers or decimals?
				if (allowRemainders.equalsIgnoreCase("y"))
					while ((!wholeOrDecimal.equalsIgnoreCase("1")) && (!wholeOrDecimal.equalsIgnoreCase("2"))) {//213
						wholeOrDecimal = JOptionPane.showInputDialog("Should the remainders be in terms of:\n1) whole numbers (ex: r2) or\n2)decimals (ex: 1.25)?\n\n"
								+ "(please choose 1 or 2)");
					}//210
				
				//if mathType=2, 5, or 7: prompt user: Is it ok if the correct answer is a negative number?
				if (mathType.equals("subtraction") || mathType.equals("both addition and subtraction") || mathType.equals("all"))
					while ((!negativeAnswer.equalsIgnoreCase("y")) && (!negativeAnswer.equalsIgnoreCase("n"))) {//219
						negativeAnswer= JOptionPane.showInputDialog("Is it ok if the correct answer is a negative number?\n\n(Y)es or (N)o");
					}//217
				
				//prompt for minimum limits for 1st & 2nd digits for custom challenge level (5)
				//minimum 1st Digit
				try {//230
					minMaxInput = JOptionPane.showInputDialog("Welcome to the Custom Setup\n\n"
					+ "What is the smallest first number you want us to choose for the challenges?\n(whole positive numbers only)\n\n"
					+ "for reference:\n"
					+ "3 is the \"first number\" in all of the following:\n"
					+ "3+1=4  3-2=1  3*2=6   3/1.5=2");
					min1stDigit = Integer.parseInt(minMaxInput);
				}//223
				catch(Exception e) {//235
					minMaxInput="";
					JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nDo not include letters, negatives, decimals, fractions, commas, etc.\n\n"
							+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
				}//231
				
				//maximum 1st digit
				try {//245
					minMaxInput = JOptionPane.showInputDialog("Welcome to the Custom Setup\n\n"
					+ "What is the largest first number you want us to choose for the challenges?\n(whole positive numbers only)\n\n"
					+ "for reference:\n"
					+ "3 is the \"first number\" in all of the following:\n"
					+ "3+1=4  3-2=1  3*2=6   3/1.5=2");
					max1stDigit = Integer.parseInt(minMaxInput);
				}//238
				catch(Exception e) {//250
					minMaxInput="";
					JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nDo not include letters, negatives, decimals, fractions, commas, etc.\n\n"
							+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
				}//246
				
				//minimum 2nd digit
				try {//260
					minMaxInput = JOptionPane.showInputDialog("Welcome to the Custom Setup\n\n"
					+ "What is the smallest second number you want us to choose for the challenges?\n(whole positive numbers only)\n\n"
					+ "for reference:\n"
					+ "3 is the \"second number\" in all of the following:\n"
					+ "1+3=4  4-3=1  2*3=6   6/3=2");
					min2ndDigit = Integer.parseInt(minMaxInput);
				}//253
				catch(Exception e) {//265
					minMaxInput="";
					JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nDo not include letters, negatives, decimals, fractions, commas, etc.\n\n"
							+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
				}//261
				
				//maximum 2nd digit
				try {//275
					minMaxInput = JOptionPane.showInputDialog("Welcome to the Custom Setup\n\n"
					+ "What is the largest second number you want us to choose for the challenges?\n(whole positive numbers only)\n\n"
					+ "for reference:\n"
					+ "3 is the \"first number\" in all of the following:\n"
					+ "1+3=4  4-3=1  2*3=6   6/3=2");
					max1stDigit = Integer.parseInt(minMaxInput);
				}//268
				catch(Exception e) {//280
					minMaxInput="";
					JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nDo not include letters, negatives, decimals, fractions, commas, etc.\n\n"
							+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
				}//276
					
				//prompt user for score value for each correct answer (include option for using the timer)
				while (correctAnswerScoreInput=="") {//293
					try {//288
						while (!correctAnswerScoreInput.matches("[0-9]+"))
							correctAnswerScoreInput = JOptionPane.showInputDialog("How many points should each correct answer count for?\n\n(whole positive numbers only)");
						correctAnswerScore = Integer.parseInt(correctAnswerScoreInput);
					}//284
					catch(Exception e) {//292
						correctAnswerScoreInput="";
						JOptionPane.showMessageDialog(null,  "Oops!\n\nPlease enter only whole positive numbers.\n\nPress OK to try again.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					}//289
				}//283
				
				//prompt user if wrong answers should deduct points
				while (wrongAnswerScoreInput=="") {//310
					try {//305
						wrongAnswerScoreInput = JOptionPane.showInputDialog("Should wrong answers deduct points?\n\n(Y)es or (N)o");
						if (wrongAnswerScoreInput.equalsIgnoreCase("n")) 
							wrongAnswerScore=0;//set wrongAnswerScore to 0 to simplify code if user chooses no
						if ((!wrongAnswerScoreInput.equalsIgnoreCase("Y")) && (!wrongAnswerScoreInput.equalsIgnoreCase("N"))){//304
							wrongAnswerScoreInput="";
							JOptionPane.showMessageDialog(null, "Oops!\n\nPlease choose only Y or N.\n\nPress OK to re-enter.", "Oops", JOptionPane.INFORMATION_MESSAGE);
						}//301
					}//297
					catch(Exception e){//309
						wrongAnswerScoreInput="";
						JOptionPane.showMessageDialog(null, "Oops!\n\nPlease choose only Y or N.\n\nPress OK to re-enter.", "Oops", JOptionPane.INFORMATION_MESSAGE);
					}//306
				}//296
				
				if (wrongAnswerScoreInput.equalsIgnoreCase("Y")) {//322
					try {//316
						wrongAnswerScoreInput = JOptionPane.showInputDialog("How many points should be deducated for each wrong answer?\n\n(whole numbers only)");
						wrongAnswerScore = Integer.parseInt(wrongAnswerScoreInput);
					}//313
					catch (Exception e){//321
						wrongAnswerScoreInput="";
						JOptionPane.showMessageDialog(null,  "Oops!\n\nPlease enter only whole numbers.\nno decimals, commas, letters, etc.\n\nPress OK to try again.", 
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
					}//317
				}//312
			}//194
				
				
			//------------------------------------------------------------------------------------------------------------------------------------
			//---------------                                    CHOOSE TIMER OPTIONS                                                  -----------
			//-----------------------------------------------------------------------------------------------------------------------------------
//									/* block temporarily disabled until timers can be set up*/
//			
//			//prompt for timer (y/n) - choosing n will skip this section, leaving timerLength w/no value
//			/*timerChoice = JOptionPane.showInputDialog("Would you like to set a time limit?\n\n(Y)es or (N)o\n");*/
//			
//			//verify that user chose y or n
//			/*while (!timerChoice.equalsIgnoreCase("Y") && !timerChoice.equalsIgnoreCase("N")) {
//				timerChoice = JOptionPane.showInputDialog(null, "Oops!\nThat option is not available.\n\n"
//						+ "Please choose Y or N.\n\n"
//						+ "Would you like to set a time limit?\n(Y)es or (N)o", 
//						  "Oops!",JOptionPane.INFORMATION_MESSAGE);
//			}*/	
//			
//			//if timerChoice=y, prompt for max time allowed
//			/*if (timerChoice.equalsIgnoreCase("y")){
//				timerLengthinput = JOptionPane.showInputDialog("How many seconds will be allowed per problem?\n"
//						+ "(please enter a whole number)\n");
//				timerLengthVerify = Double.parseDouble(timerLengthinput);
//				if (timerLengthVerify%1>0) {
//					do {
//						timerLengthinput = JOptionPane.showInputDialog(null, "Oops! We can only accept whole numbers.\n\n"
//								+ "Please enter the number of seconds that will be allowed per problem:\n", "Oops!",
//								JOptionPane.INFORMATION_MESSAGE);
//						timerLengthVerify = Double.parseDouble(timerLengthinput);					
//						}					
//					while (timerLengthVerify%1>0);
//				}
//				timerLength=(int)timerLengthVerify;
//				}*/
//	
			
			//------------------------------------------------------------------------------------------------------------------------------------
			//---------------                                    CHOOSE NUMBER OF PROBLEMS                                             -----------
			//------------------------------------------------------------------------------------------------------------------------------------
				
			//prompt user for a number of problems (or continuous play)
			while (!numOfProblemsInput.matches("[0-9]+"))
				numOfProblemsInput = JOptionPane.showInputDialog("How many problems do you want to practice?");
			numOfProblems = Integer.parseInt(numOfProblemsInput);
			
				
			//------------------------------------------------------------------------------------------------------------------------------------
			//------                                                      START OF GAME                                                     -------
			//-------------------------------------------------------------------------------------------------------------------------------------
			
			
			//switch-case for gameplay
			switch(mathType) {//1926
			
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			//------                                                       ADDITION                                                -------
			//---------------------------------------------------------------------------------------------------------------------------------------
			
			case("addition"):
				for (int x=1;x<=numOfProblems;x++) {//457
					if (challengeLevel.equals("beginner")) {//390
						//generate random numbers, single digit
						firstDigit = randNumGen1(0,10, firstDigit, challengeLevel, mathType);
						secondDigit=randNumGen2(0,10, secondDigit, challengeLevel, mathType);
					}//386
					else if (challengeLevel.equals("easy")) {//399
						//generate random numbers 10-89, without carrying when added
						do {//397
							firstDigit = randNumGen1(10,90, firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,90, secondDigit, challengeLevel, mathType);
							noCarrying = verifyNoCarry(firstDigit, secondDigit);
						}//393
						while (noCarrying==false);
					}//391
					else if (challengeLevel.equals("medium")) {//404
						//generate random numbers 0-99
						firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
					}//400
					else if (challengeLevel.equals("hard")) {//409
						//generate random numbers 2-4 digits each
						firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
					}//405
					else {//413 //if challengeLevel=5, per challenge input
						firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
					}//410
					
					//calculate answer
					correctAnswerDbl = calculateAnswerAdd(firstDigit, secondDigit);
					correctAnswer = (int)correctAnswerDbl;
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					userGuess=(int)userGuessDbl;
					
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (userGuess==correctAnswer) {//447
						//points:
						if (challengeLevel.equals("custom")){//437
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//430
						
						else {//446	///challengeLevel!=5
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//439
					}//428	
				
					//if answer is wrong
					if (userGuess!=correctAnswer) {//456
						//show correct answer with a good try message
						totalProblems++;
						JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
							+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
							+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
					}//450
				}//385
			break;
				
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                         SUBTRACTION                                                    -----------
			//------------------------------------------------------------------------------------------------------------------------------------------
			
			case ("subtraction"):
				for (int x=1;x<=numOfProblems;x++) {//579
					if (challengeLevel.equals("beginner")) {//474
						//generate random numbers, single digit, no negative answers
						do {//472
							firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,10,secondDigit, challengeLevel, mathType);
							noNegatives = verifyNoNegatives(firstDigit, secondDigit);
						}//468
						while (noNegatives==false);
					}//466
					else if (challengeLevel.equals("easy")) {//484
						//generate random numbers 10-89, without borrowing or negatives when added
						do {//482
							firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
							noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
						}//477
						while (noNegatives==false || noBorrowing==false);
					}//475
					else if (challengeLevel.equals("medium")) {//493
						//generate random numbers 0-99, no negatives, borrowing permitted
						do {//491
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
							noNegatives = verifyNoNegatives(firstDigit, secondDigit);
						}//487
						while (noNegatives==false);
					}//485
					else if (challengeLevel.equals("hard")) {//498
						//generate random numbers 2-4 digits each, borrowing & negatives permitted
						firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
					}//494
					else {//536 ///if challengeLevel=5, per challenge input
						//generate random digits, based on user input, no borrowing or negative answers allowed
						if (carryBorrow.equalsIgnoreCase("n") && negativeAnswer.equalsIgnoreCase("n")) {//509
							do {//507
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}//502
							while (noBorrowing==false || noNegatives==false);
						}//501
						
						//generate random digits, based on user input, negative answers allowed, but no borrowing
						else if (carryBorrow.equalsIgnoreCase("n")) {//519
							do {//517
								firstDigit = randNumGen1(min1stDigit,(max2ndDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min1stDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
							}//513
							while (noBorrowing==false);
						}//512
						
						//generate random digits, based on user input, borrowing allowed, no negative answers allowed
						else if (negativeAnswer.equalsIgnoreCase("n")) {//529
							do {//527
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}//523
							while (noNegatives==false);
						}//522
						
						//generate random digits, based on user input, negatives and borrowing allowed
						else {//535
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}//532
					}//499
					
					//calculate answer
					correctAnswerDbl = calculateAnswerSub(firstDigit, secondDigit);
					correctAnswer = (int)correctAnswerDbl;
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("custom")){//558
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//551
					
					else {//569	///challengeLevel!=5
						if (userGuess==correctAnswer) {//568
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//561
					}//560
				
					//if answer is wrong
					if (userGuess!=correctAnswer) {//578
						//show correct answer with a good try message
						totalProblems++;
						JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
							+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
							+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
					}//572
				}//465
			break;
		
		
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                 MULTIPLICATION                                                             -------
			//------------------------------------------------------------------------------------------------------------------------------------------	
			
			case ("multiplication"):
				for (int x=1;x<=numOfProblems;x++) {//661
					if (challengeLevel.equals("beginner")) {//595 //only 1,2,5,10,11
						//generate random numbers, 1, 2, 5, 10, and 11 sets only
						while (firstDigit!=1 && firstDigit!=2 && firstDigit!=5 && firstDigit!=10 && firstDigit!=11) {//594
							firstDigit = randNumGen1(1,12,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,12,secondDigit, challengeLevel, mathType);
						}//591
					}//589
					else if (challengeLevel.equals("easy")) {//600  //full 1-12 table
						//generate random numbers 1-12
						firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
					}//596
					else if (challengeLevel.equals("medium")) {//605
						//generate random numbers 0-99
						firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
					}//601
					else if (challengeLevel.equals("hard")) {//610
						//generate random numbers 2-4 digits each
						firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
					}//606
					else {//615 //if challengeLevel=5, per challenge input
						//generate random digits, based on user input
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}//611
					
					//calculate answer
					correctAnswerDbl = calculateAnswerMult(firstDigit, secondDigit);
					correctAnswer = (int)correctAnswerDbl;
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("custom")){//637
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//630
					
					else {//657	///challengeLevel!=5
							if (userGuess==correctAnswer) {//647
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//640	
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {//656
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//650
					}//639
					
					//reset firstDigit to allow for another random number to be generated
					firstDigit=0;
				}//588
			break;
		
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                 DIVISION                                                                   -------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			
			case ("division"):
				for (int x=1;x<=numOfProblems;x++) {//902
					if (challengeLevel.equals("beginner")) {//681  ///multiplication table, no remainders
						//generate random numbers
						do {//679
							firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
							noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
						}//673
						while (noRemainders==false);
					}//671
					else if (challengeLevel.equals("easy")) {//692  //:2 digits divided by 1 digit, no remainders
						//generate random numbers
						do {//690
							firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,10,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
							noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
						}//684
						while (noRemainders==false);
					}//682
					else if (challengeLevel.equals("medium")) {//699   //2-3 digits divided by 1-2 digits, with remainders (written as whole numbers)
						//generate random numbers
							firstDigit = randNumGen1(10,1000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,100,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
					}//693
					else if (challengeLevel.equals("hard")) {//709     //1-4 digits for each number, with remainders (written as decimals)
						//generate random numbers 1-4 digits each
						do {//707
							firstDigit = randNumGen1(1,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,10000,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
						}//702
						while (firstDigitDbl/secondDigitDbl<.01);//prevents answers <.01
					}//700
					else {//734 ///if challengeLevel=5, use challenge setup input 
						//generate random digits, based on user input, no remainders allowed
						if (allowRemainders.equalsIgnoreCase("n")) {//721
							do {//719
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);	
							}//713
							while (noRemainders==false || firstDigitDbl/secondDigitDbl<.01);
						}//712
						
						//generate random digits, based on user input, remainders allowed
						else {//733
							//generate random digits, based on user input, remainders allowed
							do {//731
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
							}//726
							while (firstDigitDbl/secondDigitDbl<.01);
						}//724
					}//710
					
					//calculate answer
					if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("1")) {//741   //whole number remainders
						correctAnswerDivPart1 = calculateAnswerDivPart1(firstDigit, secondDigit);
						correctAnswerDivPart2Dbl = calculateAnswerDivPart2Whole(firstDigit, secondDigit);
						correctAnswerDivPart2 = (int)correctAnswerDivPart2Dbl;
					}//737
					
					else if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("2")) {//743    //decimal number remainders
						correctAnswerDivPart1 = calculateAnswerDivPart1(firstDigit, secondDigit);
						correctAnswerDivPart2Dbl = calculateAnswerDivPart2Dec(firstDigit, secondDigit);
						correctAnswerDbl = Double.valueOf(correctAnswerDivPart2Dbl) + correctAnswerDivPart2Dbl;
					}//743
					
					else {//752 ///no remainders
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
					}//749
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					
					if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("1")) {//786    //whole number remainders
						
						Scanner remainderAnswer = new Scanner(System.in);
						
						while (userGuessInput.equals("")){//785
							//enter a number with remainder (expected format: 123 R4)
							userGuessInput = JOptionPane.showInputDialog("Answer format: 123 R4\n\n" + firstDigit + "/" + secondDigit + "=");
								
							//read first part of entry
							wholeNumbersEntered = remainderAnswer.next();
							//read all remaining characters
							remainderInput = remainderAnswer.nextLine();
							//keep only the numbers from the remaining characters
							remainderEntered = remainderInput.replaceAll("[^0-9]", "");
						
							//close scanner
							remainderAnswer.close();
						
							try {//779
								//change part1 & part2replaced to integers
								wholeNumberAnswer = Integer.parseInt(wholeNumbersEntered);
								remainderNumberAnswer = Integer.parseInt(remainderEntered);
							}//775
							catch(Exception e) {//784
								userGuessInput="";
								JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nEnter only numbers (no commas), a space, then R, then the remainder amount.\nEx: 12 R3"
										+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
							}//780
						}//761
					}//757
					
					else if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("2")) {//819    //decimal number remainders
						
						Scanner remainderAnswerReader = new Scanner(System.in);
						
						while (userGuessInput.equals("")){//818
							//enter a number with remainder (expected format: 123 R4)
							userGuessInput = JOptionPane.showInputDialog("Answer format: 123 R4\n\n" + firstDigit + "/" + secondDigit + "=");
								
							//read first part of entry
							wholeNumbersEntered = remainderAnswerReader.next();
							//read all remaining characters
							remainderInput = remainderAnswerReader.nextLine();
							//keep only the numbers from the remaining characters
							remainderEntered = remainderInput.replaceAll("[^0-9]", "");
							remainder = Integer.parseInt(remainderEntered);
						
							//close scanner
							remainderAnswerReader.close();
						
							try {//811
								//change part1 & part2replaced to integers
								userGuessPart1 = Integer.parseInt(wholeNumbersEntered);
								userGuessRemainder = remainder/100.0;	
							}//807
							catch(Exception e) {//816
								userGuessInput="";
								JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nEnter only numbers (no commas), a space, then R, then the remainder amount.\nEx: 12 R3"
										+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
							}//812
							userGuessDbl=userGuessPart1 + userGuessRemainder;
						}//792
					}//788
					
					else {//823    //no remainders
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					}//821
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if challenge level = 5
					if (challengeLevel.equals("custom")){//860

						//correct with no remainders or decimal remainders
						if (userGuessDbl==correctAnswerDbl && (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2"))) {//836
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + pointTotal, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//830
						
						//correct with whole number remainders
						else if ((wholeNumberAnswer==correctAnswerDivPart1) && (remainderNumberAnswer==correctAnswerDivPart2) && (wholeOrDecimal.equals("1"))) {//846
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + wholeNumbersEntered + " R" + remainderEntered 
									+ "\n\nTotal points: " + pointTotal, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//839
						
						//wrong with no remainders or decimal remainders
						totalProblems++;
						if  (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2"))
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
								+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
								+ "\n\nTotal points: " + pointTotal, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						
						//wrong with whole number remainders
						else
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + wholeNumbersEntered + " R" + remainderEntered + "\n\n"
									+ "\n\nTotal points: " + pointTotal, "Incorrect", JOptionPane.INFORMATION_MESSAGE);	
					}//827
					
					//if challenge level != 5
					else {//901  
						//correct answer
						//correct with no remainders or with decimal remainders
						if (userGuessDbl==correctAnswerDbl && (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2"))) {//873	
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//866
						
						//correct with whole number remainders
						else if ((wholeNumberAnswer==correctAnswerDivPart1) && (remainderNumberAnswer==correctAnswerDivPart2) && (wholeOrDecimal.equals("1"))) {//883
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + wholeNumbersEntered + " R" + remainderEntered 
									+ "\n\nTotal points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//876

						//if answer is wrong
						else{//900
							
							//with no remainders or with decimal remainders
							totalProblems++;
							if  (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2"))
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
									+ "\n\nTotal points: " + pointTotal, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							
							//with whole number remainders
							else
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + wholeNumbersEntered + " R" + remainderEntered + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						}//886
					}//863
				}//670
			break;
		
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                        ADDITION & SUBTRACTION                                                             -------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			
			
			case ("both addition and subtraction"):
			
				for (int x=1;x<=numOfProblems;x++) {//1109
					//choose whether to do addition or subtraction (random number generator 1=add or 2=sub)
					chooseAddorSub = randNumGen1(1,3,firstDigit, challengeLevel, mathType);
					chooseAddorSubStr = Integer.toString(chooseAddorSub);
					
					//if Addition is chosen:
					if (chooseAddorSub==1) {//991
						//generate random digits to use
						if (challengeLevel.equals("beginner")) {//925
							//generate random numbers, single digit
							firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
							secondDigit=randNumGen2(0,10,secondDigit, challengeLevel, mathType);
						}//921
						else if (challengeLevel.equals("easy")) {//934
							//generate random numbers 10-89, without carrying when added
							do {//932
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noCarrying = verifyNoCarry(firstDigit, secondDigit);
							}//928
							while (noCarrying==false);
						}//926
						else if (challengeLevel.equals("medium")) {//939
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}//935
						else if (challengeLevel.equals("hard")) {//944
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}//940
						else {//948    //if challengeLevel=5, per challenge input
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}//945
						
						//calculate answer
						correctAnswerDbl = calculateAnswerAdd(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(chooseAddorSubStr, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//970
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//963
						
						else {//990	//challengeLevel!=5
							if (userGuess==correctAnswer) {//980
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//973
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {//989
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//983
						}//972
					}//919
					
					//if Subtraction is chosen
					else if (chooseAddorSub==2){//1108
						if (challengeLevel.equals("beginner")) {//1003
							//generate random numbers, single digit, no negative answers
							do {//1001
								firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,10,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}//997
							while (noNegatives==false);
						}//995
						else if (challengeLevel.equals("easy")) {//1013
							//generate random numbers 10-89, without borrowing or negatives when added
							do {//1011
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
							}//1006
							while (noNegatives==false || noBorrowing==false);
						}//1004
						else if (challengeLevel.equals("medium")) {//1022
							//generate random numbers 0-99, no negatives, borrowing permitted
							do {//1020
								firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}//1016
							while (noNegatives==false);
						}//1014
						else if (challengeLevel.equals("hard")) {//1027
							//generate random numbers 2-4 digits each, borrowing & negatives permitted
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}//1023
						else {//1065    //if challengeLevel=5, per challenge input
							//generate random digits, based on user input, no borrowing or negative answers allowed
							if (carryBorrow.equalsIgnoreCase("n") && negativeAnswer.equalsIgnoreCase("n")) {//1038
								do {//1036
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}//1031
								while (noBorrowing==false || noNegatives==false);
							}//1030
							
							//generate random digits, based on user input, negative answers allowed, but no borrowing
							else if (carryBorrow.equalsIgnoreCase("n")) {//1048
								do {//1046
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
								}//1042
								while (noBorrowing==false);
							}//1041
							
							//generate random digits, based on user input, borrowing allowed, no negative answers allowed
							else if (negativeAnswer.equalsIgnoreCase("n")) {//1058
								do {//1056
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}//1052
								while (noNegatives==false);
							}//1051
							
							//generate random digits, based on user input, negatives and borrowing allowed
							else {//1064
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}//1061
						}//1028
					
					//calculate answer
					correctAnswer = calculateAnswerSub(firstDigit, secondDigit);
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					
					userGuessDbl = answerPrompt(chooseAddorSubStr, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("custom")){//1086
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//1079
					
					else {//1107	///challengeLevel!=5
						if (userGuess==correctAnswer) {//1096
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "-" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1089
					
						//if answer is wrong
						if (userGuess!=correctAnswer) {//1106
							//show correct answer with a good try message
							totalProblems++;
							pointTotal-=wrongAnswerScore;
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
								+ firstDigit + " - " + secondDigit + " = " + correctAnswer + "\n\n"
								+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						}//1099
					}//1088
					}//994
			}//913
			break;			

			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                        MULTIPLICATION & DIVISION                                                           -------
			//------------------------------------------------------------------------------------------------------------------------------------------			

			
			case("both multiplication and division"):
				
				for (int x=1;x<=numOfProblems;x++) {//1405
					//choose whether to do multiplication or division (random number generator 1=add or 2=sub)
					Random multOrDiv = new Random();
					chooseMultorDiv = multOrDiv.nextInt(1,3);
					chooseMultorDivStr = Integer.toString(chooseMultorDiv);
					
					//if Multiplication is chosen
					if (chooseMultorDiv==1) {//1244
						mathType.equals("multiplication"); //reassigned to allow randNumGen1/randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("beginner")) {//1136    ///only 1,2,5,10,11
							//generate random numbers, 1, 2, 5, 10, and 11 sets only
							while (firstDigit!=1 && firstDigit!=2 && firstDigit!=5 && firstDigit!=10 && firstDigit!=11) {//1135
								firstDigit = randNumGen1(1,12,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,12,secondDigit, challengeLevel, mathType);
							}//1132
						}//1130
						else if (challengeLevel.equals("easy")) {//1141    ///full 1-12 table
							//generate random numbers 1-12
							firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
						}//1137
						else if (challengeLevel.equals("medium")) {//1146
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}//1142
						else if (challengeLevel.equals("hard")) {//1151
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}//1147
						else {//1156    ///if challengeLevel=5, per challenge input
							//generate random digits, based on user input
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}//1152
						
						//calculate answer
						correctAnswerDbl = calculateAnswerMult(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1178
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1171
						
						else {//1198	///challengeLevel!=5
								if (userGuess==correctAnswer) {//1188
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//1181
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {//1197
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								}//1191
						}//1180
						
						//reset firstDigit to allow for another random number to be generated
						firstDigit=0;
					
						//calculate answer
						correctAnswer = calculateAnswerMult(firstDigit, secondDigit);
					
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(chooseMultorDivStr, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1222
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1215
						
						else {//1243	//challengeLevel!=5
							if (userGuess==correctAnswer) {//1232
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//1225
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {//1242
								//show correct answer with a good try message
								totalProblems++;
								pointTotal-=wrongAnswerScore;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//1235
						}//1224
					}//1127
					
					//if division is chosen
					if (chooseMultorDiv==2) {//1404
						mathType.equals("division"); //reassigned to allow randNumGen1/randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("beginner")) {//1260     ///multiplication table, no remainders
							//generate random numbers
							do {//1258
								firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}//1252
							while (noRemainders==false);
						}//1250
						else if (challengeLevel.equals("easy")) {//1271    ///:2 digits divided by 1 digit, no remainders
							//generate random numbers
							do {//1269
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}//1263
							while (noRemainders==false);
						}//1261
						else if (challengeLevel.equals("medium")) {//1278      ///2-3 digits divided by 1-2 digits, with remainders (written as whole numbers)
							//generate random numbers
								firstDigit = randNumGen1(10,1000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,100,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
						}//1272
						else if (challengeLevel.equals("hard")) {//1289     ///1-4 digits for each number, with remainders (written as decimals)
							//generate random numbers 1-4 digits each
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							do {//1287 
								firstDigit = randNumGen1(1,10000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10000,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
							}//1282
							while (firstDigit%secondDigit<.01);//prevents most answers less than .01
						}//1279
						else {//1316     ///if challengeLevel=5, use challenge setup input 
							//generate random digits, based on user input, no remainders allowed
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							if (allowRemainders.equalsIgnoreCase("n")) {//1302
								do {//1300
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
									noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);	
								}//1294
								while (noRemainders==false);
							}//1293
							
							//generate random digits, based on user input, remainders allowed
							else {//1315
								//generate random digits, based on user input, remainders allowed
								//set up a do/while to filter out answers that are less than .1****************************************************INCOMPLETE SECTION
								do {//1313
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
								}//1308
								while (firstDigitDbl/secondDigitDbl<.1);
							}//1305
						}//1290
						//calculate answer
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1337
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1330
						
						else {//1357	///challengeLevel!=5
								if (userGuess==correctAnswer) {//1347
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//1340
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {//1356
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " / " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								}//1350
						}//1339
						
						//reset firstDigit to allow for another random number to be generated
						firstDigit=0;
					
						//calculate answer
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						//for challengelevel 3 or 5, calculate whole number remainder
						//for challengelevel 4 or 5(with decimalremainders), control for 3 decimal places max
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1382
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1375
						
						else {//1402	//challengeLevel!=5
							if (userGuessDbl==correctAnswerDbl) {//1392
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//1385	
						
							//if answer is wrong
							if (userGuessDbl!=correctAnswerDbl) {//1401
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//1395
						}//1384
					}//1247
				}//1120
			break;			

			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                 ALL                                                                        -------
			//------------------------------------------------------------------------------------------------------------------------------------------			

			
			case ("all"):
			//use a number generator to choose whether to do add, sub, mult, or div
				for (int x=1;x<=numOfProblems;x++) {//1895
					//choose whether to do addition or subtraction (random number generator 1=add or 2=sub)
					Random randomAddSubMultDiv = new Random();
					chooseAddSubMultDiv = randomAddSubMultDiv.nextInt(1,5);
					chooseAddSubMultDivStr = Integer.toString(chooseAddSubMultDiv);
					
					//if addition is chosen
					if (chooseAddSubMultDiv==1) {//1496
						mathType.equals("addition"); //changed to allow randNumGen1,randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("beginner")) {//1430
							//generate random numbers, single digit
							firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
							secondDigit=randNumGen2(0,10,secondDigit, challengeLevel, mathType);
						}//1426
						else if (challengeLevel.equals("easy")) {//1439
							//generate random numbers 10-89, without carrying when added
							do {//1437
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noCarrying = verifyNoCarry(firstDigit, secondDigit);
							}//1433
							while (noCarrying==false);
						}//1431
						else if (challengeLevel.equals("medium")) {//1444
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}//1440
						else if (challengeLevel.equals("hard")) {//1449
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}//1445
						else {//1453   ///if challengeLevel=5, per challenge input
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}//1450
						
						//calculate answer
						correctAnswerDbl = calculateAnswerAdd(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(chooseAddSubMultDivStr, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1475
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1468
						
						else {//1495	///challengeLevel!=5
							if (userGuess==correctAnswer) {//1485
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//1478
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {//1494
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//1488
						}//1477
					}//1423
					
					//if Subtraction is chosen
					else if (chooseAddSubMultDiv==2){//1614
						mathType.equals("subtraction");  //changed to allow randNumGen1,randNumGen2 functions to work
						if (challengeLevel.equals("beginner")) {//1509
							//generate random numbers, single digit, no negative answers
							do {//1507
								firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,10,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}//1503
							while (noNegatives==false);
						}//1501
						else if (challengeLevel.equals("easy")) {//1519
							//generate random numbers 10-89, without borrowing or negatives when added
							do {//1517
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
							}//1512
							while (noNegatives==false || noBorrowing==false);
						}//1510
						else if (challengeLevel.equals("medium")) {//1528
							//generate random numbers 0-99, no negatives, borrowing permitted
							do {//1526
								firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}//1522
							while (noNegatives==false);
						}//1520
						else if (challengeLevel.equals("hard")) {//1533
							//generate random numbers 2-4 digits each, borrowing & negatives permitted
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}//1529
						else {//1571     //if challengeLevel=5, per challenge input
							//generate random digits, based on user input, no borrowing or negative answers allowed
							if (carryBorrow.equalsIgnoreCase("n") && negativeAnswer.equalsIgnoreCase("n")) {//1544
								do {//1542
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}//1537
								while (noBorrowing==false || noNegatives==false);
							}//1536
							
							//generate random digits, based on user input, negative answers allowed, but no borrowing
							else if (carryBorrow.equalsIgnoreCase("n")) {//1554
								do {//1552
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
								}//1548
								while (noBorrowing==false);
							}//1547
							
							//generate random digits, based on user input, borrowing allowed, no negative answers allowed
							else if (negativeAnswer.equalsIgnoreCase("n")) {//1564
								do {//1562
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}//1558
								while (noNegatives==false);
							}//1557
							
							//generate random digits, based on user input, negatives and borrowing allowed
							else {//1570
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}//1567
						}//1534
					
					//calculate answer
					correctAnswer = calculateAnswerSub(firstDigit, secondDigit);
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					
					userGuessDbl = answerPrompt(chooseAddSubMultDivStr, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("custom")){//1592
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//1585
					
					else {//1613	///challengeLevel!=5
						if (userGuess==correctAnswer) {//1602
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "-" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1595
					
						//if answer is wrong
						if (userGuess!=correctAnswer) {//1612
							//show correct answer with a good try message
							totalProblems++;
							pointTotal-=wrongAnswerScore;
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
								+ firstDigit + " - " + secondDigit + " = " + correctAnswer + "\n\n"
								+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						}//1605
					}//1594
				}//1499
					
					//if multiplication is chosen
					if (chooseAddSubMultDiv==3) {//1734
						mathType.equals("multiplication");  //changed to allow randNumGen1,randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("beginner")) //only 1,2,5,10,11
							//generate random numbers, 1, 2, 5, 10, and 11 sets only
							while (firstDigit!=1 && firstDigit!=2 && firstDigit!=5 && firstDigit!=10 && firstDigit!=11) {//1625
								firstDigit = randNumGen1(1,12,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,12,secondDigit, challengeLevel, mathType);
							}//1622
						else if (challengeLevel.equals("easy")) {//1630    ///full 1-12 table
							//generate random numbers 1-12
							firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
						}//1626
						else if (challengeLevel.equals("medium")) {//1635
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}//1631
						else if (challengeLevel.equals("hard")) {//1640
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}//1636
						else {//1645   //if challengeLevel=5, per challenge input
							//generate random digits, based on user input
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}//1641
						
						//calculate answer
						correctAnswerDbl = calculateAnswerMult(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1667
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1660
						
						else {//1687  	///challengeLevel!=5
								if (userGuess==correctAnswer) {//1677
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//1670
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {//1686
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								}//1680
						}//1669
						
						//reset firstDigit to allow for another random number to be generated
						firstDigit=0;
					
						//calculate answer
						correctAnswer = calculateAnswerMult(firstDigit, secondDigit);
					
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(chooseAddSubMultDivStr, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1711
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1704
						
						else {//1733 	///challengeLevel!=5
							if (userGuess==correctAnswer) {//1722
								//points:
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//1714
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {//1732
								//show correct answer with a good try message
								totalProblems++;
								pointTotal-=wrongAnswerScore;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//1725
						}//1713
					}//1617
					
					//if division is chosen
					if (chooseAddSubMultDiv==4) {//1894
						mathType.equals("division");  //changed to allow randNumGen1,randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("beginner")) {//1750   ///multiplication table, no remainders
							//generate random numbers
							do {//1748
								firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}//1742
							while (noRemainders==false);
						}//1740
						else if (challengeLevel.equals("easy")) {//1761     ///:2 digits divided by 1 digit, no remainders
							//generate random numbers
							do {//1759
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}//1753
							while (noRemainders==false);
						}//1751
						else if (challengeLevel.equals("medium")) {//1768      //2-3 digits divided by 1-2 digits, with remainders (written as whole numbers)
							//generate random numbers
								firstDigit = randNumGen1(10,1000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,100,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
						}//1762
						else if (challengeLevel.equals("hard")) {//1779    ///1-4 digits for each number, with remainders (written as decimals)
							//generate random numbers 1-4 digits each
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							do {//1777
								firstDigit = randNumGen1(1,10000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10000,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
							}//1772
							while (firstDigit%secondDigit<.01);//prevents most answers less than .01
						}//1769
						else {//1806     ///if challengeLevel=5, use challenge setup input 
							//generate random digits, based on user input, no remainders allowed
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							if (allowRemainders.equalsIgnoreCase("n")) {//1792
								do {//1790
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
									noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);	
								}//1784
								while (noRemainders==false);
							}//1783
							
							//generate random digits, based on user input, remainders allowed
							else {//1805
								//generate random digits, based on user input, remainders allowed
								//set up a do/while to filter out answers that are less than .1****************************************************INCOMPLETE SECTION
								do {//1803
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
									}//1798
								while(firstDigitDbl/secondDigitDbl<.1);
							}//1795
						}//1780
						
						//calculate answer
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1828
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1821
						
						else {//1848	///challengeLevel!=custom
							if (userGuess==correctAnswer) {//1838
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//1831
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {//1847
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//1841
						}//1830
						
						//reset firstDigit to allow for another random number to be generated
						firstDigit=0;
					
						//calculate answer
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						//for challengelevel 3 or 5, calculate whole number remainder
						//for challengelevel 4 or 5(with decimalremainders), control for 3 decimal places max
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("custom")){//1873
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//1866
						
						else {//1893	///challengeLevel!=5
							if (userGuessDbl==correctAnswerDbl) {//1883
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//1876
						
							//if answer is wrong
							else /*(userGuessDbl!=correctAnswerDbl)*/ {//1892
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}//1886
						}//1875
					}//1737	
			}//1416
			break;

			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                      SEE HIGH SCORES                                                                       -------
			//------------------------------------------------------------------------------------------------------------------------------------------			

						
			//see high scores
			case ("see high scores"):
				//show high scores file
				JOptionPane.showMessageDialog(null, "This option has not been set up yet.", "Coming soon", JOptionPane.WARNING_MESSAGE);
				
				line = null; //temp variable to get BufferedReader working
				
				try
		        {//1920
		            FileReader highScoresReader = new FileReader("highscores.txt");
		            BufferedReader bufferedReaderForHighScores = new BufferedReader(highScoresReader);
		            
		            while((line = bufferedReaderForHighScores.readLine()) != null)
		                System.out.println(line);
		            
		            bufferedReaderForHighScores.close();
		        }//1912
		        catch(IOException ex) {//1923
		        	JOptionPane.showMessageDialog(null, "Uh oh!\nSomething went wrong.\n\n(exception: " + ex, "Error", JOptionPane.ERROR_MESSAGE, null);
		        }//1921
			break;
	
			}//377
		
					
			//-------------------------------------------------------------------------------------------------------------------------------------------------------
			//-------------                                               DISPLAY SCORE & OFFER REPLAY                                               ----------------
			//-------------------------------------------------------------------------------------------------------------------------------------------------------
					
			
			//------------------------------------------------------------------------
			//------                  DISPLAY SCORE                              -----
			//------------------------------------------------------------------------
				
			finalScore = (Double.valueOf(totalCorrectAnswers))/(Double.valueOf(totalProblems));
			
			//store final scores (username, finalScore, totalCorrectAnswers, totalProblems)
			try {//1945
				FileWriter highScoresFW = new FileWriter("highscores.txt");
				highScoresFW.write(username + "\n" + finalScore + "\n" + totalCorrectAnswers + "\n" + totalProblems);
				highScoresFW.close();
			}//1941
			catch (Exception e) {//1948
				JOptionPane.showMessageDialog(null, "Something went wrong.", "uh oh", JOptionPane.ERROR_MESSAGE);
			}//1946
			
			//display final scores
			if (finalScore==1) 
				JOptionPane.showMessageDialog(null, "PERFECT SCORE!\n\n You answered all " + totalCorrectAnswers + " problems correctly!");
			else if (finalScore>=0.9 && finalScore<1)
				JOptionPane.showMessageDialog(null,"That was amazing!\n\nYou answered " + totalCorrectAnswers + " out of " + totalProblems + " problems correctly!");
			else if (finalScore>0.5 && finalScore<0.9)
				JOptionPane.showMessageDialog(null, "Good job!\n\nYou answered " + totalCorrectAnswers + " out of " + totalProblems + " problems correctly!");
			else
				JOptionPane.showMessageDialog(null, "Good try.\n\nYou answered " + totalCorrectAnswers + " out of " + totalProblems + " problems correctly!");
			
			
				
			//------------------------------------------------------------------------
			//------                  PLAY AGAIN?                                -----
			//------------------------------------------------------------------------
			
			//add a way to play again
			do {//1974
				//prompt for play again? y/n (three options: no, playAgain same settings, playAgain new settings) // ***********************************NEED CUSTOM BUTTONS MADE
				playAgain = JOptionPane.showInputDialog("Do you want to play again?\n\n(Y)es or (N)o");
				//if invalid input is entered, display error message
				if ((!playAgain.equalsIgnoreCase("y")) && (!playAgain.equalsIgnoreCase("n")))
					JOptionPane.showMessageDialog(null, "Oops! Please enter a number.\n\nPress OK to return to the \"Play Again?\" menu", "Oops", 
							JOptionPane.INFORMATION_MESSAGE);
			}//1967
			while ((!playAgain.equalsIgnoreCase("y")) && (!playAgain.equalsIgnoreCase("n")));
				
			//store scores from current round in score array
			scoreRecord.add(pointTotal);
			scoreRecord.add(totalCorrectAnswers);
			scoreRecord.add(totalProblems);
			int finalScoreInt = (int)(finalScore*100);
			scoreRecord.add(finalScoreInt);	
			
			if (playAgain.equalsIgnoreCase("Y")){//2036
				//accumulate number of rounds
				roundCount++;
		
				//reset all accumulators except roundCount
				finalScore=0;
				numOfProblems=0;
				firstDigit=0;
				secondDigit=0;
				userGuess=0;
				userGuessDbl=0;
				pointTotal=0;
				totalProblems=0;
				totalCorrectAnswers=0;
					
				// if challengeLevel = 5, prompt for re-using same custom settings again
				if (challengeLevel.equals("custom")) {//2035
					saveCustomSettings = JOptionPane.showInputDialog(null, "Would you like to keep using the same custom settings?", "Keep Custom Settings?", JOptionPane.YES_NO_OPTION);
					if (Integer.parseInt(saveCustomSettings) == JOptionPane.YES_OPTION) {//2019
						//save settings to settingsArray
						customSettingsArrayInt[0] = min1stDigit;
						customSettingsArrayInt[1] = max1stDigit;
						customSettingsArrayInt[2] = min2ndDigit;
						customSettingsArrayInt[3] = max2ndDigit;
						customSettingsArrayStr[0] = carryBorrow;
						customSettingsArrayStr[1] = allowRemainders;
						customSettingsArrayStr[2] = wholeOrDecimal;
						customSettingsArrayStr[3] = negativeAnswer;
						customSettingsArrayStr[4] = correctAnswerScoreInput;
						customSettingsArrayInt[4] = correctAnswerScore;
						customSettingsArrayStr[5] = wrongAnswerScoreInput;
						customSettingsArrayInt[6] = wrongAnswerScore;
					}//2005
					else {//2034     ///if saveCustomSettings = no, reset all custom variables
						//reset all custom setting variables to defaults
						min1stDigit = 0;
						max1stDigit = 0;
						min2ndDigit = 0;
						max2ndDigit = 0;
						carryBorrow = "";
						allowRemainders = "";
						wholeOrDecimal = "";
						negativeAnswer = "";
						correctAnswerScoreInput = "";
						correctAnswerScore = 0;
						wrongAnswerScoreInput = "";
						wrongAnswerScore = 0;
					}//2020
				}//2003
			}//1984		  	
		}//84 (play loop, while playAgain==y)
		
		
		//when user is done playing, show data for all rounds played this time
		scoreOutputCounter=0;
		do{//2050
			scoreOutput = scoreOutput  //initialized to empty string in variable assignments
				+ "\n\nRound " + (roundNumDisplayCounter) + ": " 
				+ "\n\tCorrect Answers: " + scoreRecord.get(scoreOutputCounter + 1) //index 1 in first set 
				+ "\n\tTotal Problems: " + scoreRecord.get(scoreOutputCounter+2) //index 2 in first set
				+ "\n\tTotal Points: " + scoreRecord.get(scoreOutputCounter) //index 0 in first set
				+ "\n\tAccuracy: " + scoreRecord.get(scoreOutputCounter+3) + "%";  //index 3 in first set 
			scoreOutputCounter+=4; //data from each round is a set of 4 items
			roundNumDisplayCounter++;
		}//2040
		
		//if playAgain==n, show final scores from all rounds
		while (scoreOutputCounter<=(roundCount*4-4)); //original: (scoreOutputCounter<((roundCount*4)-1))
	
		//Display Final Stats to Player
		//set message to be displayed
	  	String finalUserData = "Your Final Scores:\n\n" + scoreOutput;
	
	  	//create a JTextArea to hold the message
	  	JTextArea textArea = new JTextArea(25, 40); //(height, width)
	  	textArea.setText(finalUserData);
	  	textArea.setEditable(false);
	  	
	  	//wrap a scrollpane around the textArea
	  	JScrollPane scrollPane = new JScrollPane(textArea);
	  	scrollPane.createVerticalScrollBar();
	  	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	  	//display scrollPane (with text area inside it) in a message dialog
	  	JOptionPane.showMessageDialog(null, scrollPane);
	  	
	  	//add a high score file for each level, and for overall (based on percentage of questions answered correctly)****************************************************
	}//20 (main)

	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------                                                                      FUNCTIONS                                                                          -------
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//prompt for answer input
	//verify that input is integer for add/sub/mult
	public static double answerPrompt(String mmc, int x, int y) {//2109
		String userGuessInput="";
		double z=0;
		while (userGuessInput=="") {//2107
			try {//2101
				if (mmc.equals("addition")) {//2088
					userGuessInput = JOptionPane.showInputDialog(x + "+" + y + "=\n\nEnter numbers only.\n(no commas)");
					z = Double.parseDouble(userGuessInput);
				}//2085
				if (mmc.equals("subtraction")) {//2092
					userGuessInput = JOptionPane.showInputDialog(x + "-" + y + "= \n\nOnly numbers and negative signs can be entered.\n(no commas)");
					z = Double.parseDouble(userGuessInput);
				}//2089
				if (mmc.equals("multiplication")) {//2096
					userGuessInput = JOptionPane.showInputDialog(x + "x" + y + "=\n\nEnter numbers only.\n(no commas)");
					z = Double.parseDouble(userGuessInput);
				}//2093
				if (mmc.equals("division")) {//2100
					userGuessInput = JOptionPane.showInputDialog(x + "/" + y + "=");
					z = Double.parseDouble(userGuessInput);
				}//2097
			}//2084
			catch(Exception e) {//2106
				userGuessInput="";
				JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nNo letters, commas, or other stuff will work.\n\n\n"
						+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
			}//2102
		}//2083
		return z;
	}//2080
	
	//prompt for userGuess for division, where whole number remainders are allowed
	public static int answerPromptDivRemaindersPart1(int x, int y) {//2135
		Scanner remainderAnswer = new Scanner(System.in);
		
		int z=0;
		String userGuessInput="", a;
		
		while (userGuessInput.equals("")){//2132
			try {//2126
				//enter a number with remainder (format expected: 123 R4)
				userGuessInput = JOptionPane.showInputDialog("Answer format: 123 R4\n\n" + x + "/" + y + "=");
					
				//read first part of entry
				a = remainderAnswer.next();
				z = Integer.parseInt(a);
			}//2119
			catch(Exception e) {//2131
				userGuessInput="";
				JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nEnter only numbers (no commas), then R, then the remainder amount.\nEx: 12 R3"
						+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
			}//2127
		}//2118
		remainderAnswer.close();
		return z;
	}//2112
	
	//find whole number remainder for division problem
	public static int answerPromptDivRemaindersPart2(int x, int y) {//2144
		double a = Double.valueOf(x);
		double b = Double.valueOf(y);
		double c = a%b;
		int z = (int)c;
		return z;
	}//2138
	
	//calculate answer
	public static int calculateAnswerAdd (int x, int y) {//2149
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (x+y)); //*************************************************************************************TEST ONLY
		return (x + y);
	}//2147

	//calculate answer for division without remainders
	public static double calculateAnswerDiv(double x, double y) {//2156
		double z = x/y;
		z = (Math.round(z*100.0))/100.0; //move decimal place 2 to the right, round last digit, move decimal back left 2 spots
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (z)); //*************************************************************************************TEST ONLY
		return z;
	}//2152
	
	//calculate answer for division - whole number portion
	public static int calculateAnswerDivPart1(int x, int y) {//2161
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (x/y)); //*************************************************************************************TEST ONLY
		return (x/y);
	}//2159
	
	//calculate answer for division - decimal remainder portion
	public static double calculateAnswerDivPart2Dec(int x, int y) {//2171
		double a = Double.valueOf(x);
		double b = Double.valueOf(y);
		double c = (a/b) - (int)(a/b);
		c = Math.round(c*100);
		double z = c/100;
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (z)); //*************************************************************************************TEST ONLY
		return z;
	}//2164
	
	//calculate answer for division = whole number remainder portion
	public static int calculateAnswerDivPart2Whole(int x, int y) {//2181
		double a = Double.valueOf(x);
		double b = Double.valueOf(y);
		double c = (a/b) - (int)(a/b);
		c = Math.round(c*100);
		int z = (int)c;	
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (z)); //*************************************************************************************TEST ONLY
		return z;
	}//2174
		
	//calculate answer for subtraction
	public static int calculateAnswerSub(int x, int y) {//2186
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (x-y)); //*************************************************************************************TEST ONLY
		return (x-y);
	}//2184
	
	//calculate answer for multiplication
	public static int calculateAnswerMult(int x, int y) {//2191
		JOptionPane.showMessageDialog(null, "TEST -->  Answer = " + (x*y)); //*************************************************************************************TEST ONLY
		return (x*y);
	}//2189
		
	//choose random number for firstDigit
	public static int randNumGen1 (int min, int max, int old1st, String challLevel, String typeOfMath) {//2243
		boolean test=false;
		int z=0;
		
		//test to prevent repetitive random numbers being returned
		while (test==false){//2241
			Random randomGen = new Random();
			
			//choose the random single digit
			z = randomGen.nextInt(min, max);
			
			if (challLevel.equals("beginner")) {//2212
				if ((typeOfMath.equals("addition") || typeOfMath.equals("subtraction") || typeOfMath.equals("both addition and subtraction")) && 
						((z>=old1st && (z-2)<old1st) || (z<=old1st && (z+2)>old1st )))
					test=false;
				else if ((typeOfMath.equals("multiplication") || typeOfMath.equals("division") || typeOfMath.equals("both multiplication and division")) && z==old1st) 
					test = false;
				else 
					test=true;
			}//2205
			else if (challLevel.equals("2")) {//2222
				if ((typeOfMath.equals("addition") || typeOfMath.equals("subtraction") || typeOfMath.equals("both addition and subtraction")) && 
						((z>=old1st && (z-5)<old1st) || (z<=old1st && (z+5)>old1st ))) 
					test=false;
				else if (typeOfMath.equals("multiplication") && z==old1st) 
					test=false;
				else if (typeOfMath.equals("division") && ((z>=old1st && (z-7)<old1st) || (z<=old1st && (z+7)>old1st )))
					test=false;
				else 
					test=true;
			}//2213
			else if (challLevel.equals("medium")) {//2230
				if ((typeOfMath.equals("addition") || typeOfMath.equals("subtraction") || typeOfMath.equals("both addition and subtraction")) && 
						((z>=old1st && (z-7)<old1st) || (z<=old1st && (z+7)>old1st ))) 
					test=false;
				else if ((typeOfMath.equals("multiplication") || typeOfMath.equals("division") || typeOfMath.equals("both multiplication and division")) && 
						((z>=old1st && (z-7)<old1st) || (z<=old1st && (z+7)>old1st ))) 
					test=false;
				else 
					test=true;
			}//2223		
			else if (challLevel.equals("hard")) {//2236
				if ((z>=old1st && (z-123)<old1st) || (z<=old1st && (z+123)>old1st)) 
					test=false;
				else 
					test=true;
			}//2231
			else 
				test=true;
			
			//challengeLevel 5 not tested at all
		}//2199
		return z;
	}//2194
	
	//choose random number for secondDigit
	public static int randNumGen2 (int min, int max, int old2nd, String challLevel, String typeOfMath) {//2298
		boolean test=false;
		int z=0;
		
		//test to prevent repetitive random numbers being returned
		while (test==false){//2296
			Random randomGen = new Random();
			
			//choose the random single digit
			z = randomGen.nextInt(min, max);

			if (challLevel.equals("beginner")) {//2266
				if ((typeOfMath.equals("addition") || typeOfMath.equals("subtraction") || typeOfMath.equals("both addition and subtraction")) && 
						((z>=old2nd && (z-2)<old2nd) || (z<=old2nd && (z+2)>old2nd ))) 
					test=false;
				else if (typeOfMath.equals("multiplication") && (((z>=old2nd && (z-2)<old2nd) || (z<=old2nd && (z+2)>old2nd )))) 
					test=false;
				else if (typeOfMath.equals("division") && z==old2nd) 
					test=false;
				else 
					test=true;
			}//2257
			else if (challLevel.equals("easy")) {//2277
				//typeOfMath=6 is validated through converting the mathType to 3 or 4 in main
				if ((typeOfMath.equals("addition") || typeOfMath.equals("subtraction") || typeOfMath.equals("both addition and subtraction")) && 
						((z>=old2nd && (z-5)<old2nd) || (z<=old2nd && (z+5)>old2nd ))) 
					test=false;
				else if (typeOfMath.equals("multiplication") && z==old2nd) 
					test=false;
				else if (typeOfMath.equals("division") && ((z>=old2nd && (z-7)<old2nd) || (z<=old2nd && (z+7)>old2nd ))) 
					test=false;
				else 
					test=true;
			}//2267
			else if (challLevel.equals("medium")) {//2285
				if ((typeOfMath.equals("addition") || typeOfMath.equals("subtraction") || typeOfMath.equals("both addition and subtraction")) && 
						((z>=old2nd && (z-7)<old2nd) || (z<=old2nd && (z+7)>old2nd )))
					test=false;
				else if ((typeOfMath.equals("multiplication") || typeOfMath.equals("division") || typeOfMath.equals("both multiplication and division")) && 
						((z>=old2nd && (z-7)<old2nd) || (z<=old2nd && (z+7)>old2nd )))
					test=false;
				else 
					test=true;
			}//2278
			else if (challLevel.equals("hard")) {//2292
				//z<+/-123 from old2nd, test=false
				if ((z>=old2nd && (z-123)<old2nd) || (z<=old2nd && (z+123)>old2nd )) 
					test=false;
				else 
					test=true;
			}//2286
			else 
				test=true;
			//challengeLevel 5 not tested at all
		}//2251
		return z;
	}//2246
	
	//verify subtraction problems have no borrowing needed
	//************************************************************************************************************************THIS DOES NOT WORK TO VERIFY THAT THERE IS NO BORROWING
	public static boolean verifyNoBorrowing(int x, int y) {//2317
			//going right to left, check if x-y<0 for any of them - if it is, return a z=false
	        boolean z=true;
	        String stringFirstDigit = Integer.toString(x); //firstDigit to string
			int num1 = Integer.parseInt(stringFirstDigit);
			do {//2314
				int lastDigitx = num1 % 10;
				num1=num1/10;
				int lastDigity = y % 10;
				y=y/10;
				if (lastDigitx-lastDigity<0) 
					z=false;
			}//2307
			while (num1>0);
			return z;
	}//2302
		
	//verify addition problems have no carrying
	public static boolean verifyNoCarry(int x, int y) {//2336
		//if ones or tens place is >9 when added, choose new numbers
		String stringFirstDigit = Integer.toString(x); //firstDigit to string
		char onesPlaceFirstDigitChar = stringFirstDigit.charAt(1); //ones place in char, 1st digit 
		char tensPlaceFirstDigitChar = stringFirstDigit.charAt(0); //tens place in char, 1st digit
		int firstDigitOnesPlace = Character.getNumericValue(onesPlaceFirstDigitChar); //1st digit ones place int
		int firstDigitTensPlace = Character.getNumericValue(tensPlaceFirstDigitChar); //1st digit tens place int
		String stringSecondDigit = Integer.toString(y); //secondDigit to string
		char onesPlaceSecondDigitChar = stringSecondDigit.charAt(1); //2nd digit ones place as char
		char tensPlaceSecondDigitChar = stringSecondDigit.charAt(0); //2nd digit tens place as char
		int secondDigitOnesPlace = Character.getNumericValue(onesPlaceSecondDigitChar); //2nd digit ones place int
		int secondDigitTensPlace = Character.getNumericValue(tensPlaceSecondDigitChar); //2nd digit tens place int
		boolean z=false;
		if (firstDigitOnesPlace+secondDigitOnesPlace<=9 || firstDigitTensPlace+secondDigitTensPlace<=9) 
			z=true;
		return z;
	}//2320
	
	//verify subtraction problems have no negative answers
		public static boolean verifyNoNegatives(int x, int y) {//2344
			boolean z=false;
			if (x-y>=0) 
				z=true;
			return z;
		}//2339
		
	//verify no remainders for division
	public static boolean verifyNoRemainders(double x, double y) {//2353
		boolean z=true;
		double test = x%y;
		if (test!=0) 
			z=false;
		return z;
	}//2347	
}//18