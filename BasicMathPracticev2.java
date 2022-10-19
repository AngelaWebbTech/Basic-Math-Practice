// 7.1.22 timer starts/stops needs to be added (make this a function that is called in each section if needed)
//more than 3 rounds, the final output runs off screen. add JScrollPane
//7.20.22 (fileWriter/printWriter) need to add an option to save the custom level setup
//7.24.22 timer does not determine points - timer keeps track of time as a measure of speed, points are added same as no-timer method in all levels
//8.5.22 function (randNumGen1)://challengeLevel 5 not tested for good variety at all
//8.5.22 function(randNumGen2): //challengeLevel 5 not tested for good variety at all

//practice basic + - x % math

import javax.swing.JOptionPane; //for UI
import java.util.Random; //for random number generator
import java.util.ArrayList; //for storing data for each round
import java.util.Scanner; //for reading answer inputs for division
import java.io.FileWriter; //filewriter for high scores file
import java.io.*; //for FileReader in case(8)

public class BasicMathPracticev2 {

	public static void main(String[] args) {
		
		//--------------------------------------------------------------------------------------------------------------------------------------
		//-------------------                            GLOBAL VARIABLES                                                     ------------------
		//--------------------------------------------------------------------------------------------------------------------------------------
		
		double correctAnswerDbl=0, correctAnswerDivPart2Dbl, finalScore, firstDigitDbl, secondDigitDbl, userGuessDbl=0, userGuessRemainder=0.0;
		
		int chooseAddorSub, chooseAddSubMultDiv,chooseMultorDiv, correctAnswer, correctAnswerDivPart1=0, correctAnswerDivPart2=0, correctAnswerScore=0, firstDigit=0, 
				max1stDigit=0, max2ndDigit=0, min1stDigit=0, min2ndDigit=0, numOfProblems, overallScore=0, pointTotal=0, remainder, remainderNumberAnswer=0, 
				roundCount=1, roundNumDisplayCounter=1, scoreOutputCounter, secondDigit=0, totalCorrectAnswers=0, totalProblems=0, userGuess, userGuessPart1=0, 
				wholeNumberAnswer=0, wrongAnswerScore=0;
		
		String allowRemainders="", carryBorrow="", challengeLevel, chooseAddorSubStr, chooseAddSubMultDivStr, chooseMultorDivStr, correctAnswerScoreInput="", mathType="", 
				negativeAnswer="", numOfProblemsInput, playAgain="Y", remainderEntered="", remainderInput, scoreOutput="", userGuessInput="", 
				username, wholeNumbersEntered="", wholeOrDecimal="", wrongAnswerScoreInput="", line;
		
		boolean noBorrowing, noCarrying, noNegatives, noRemainders;
		
		
		//--------------------------------------------------------------------------------------------------------------------------------------
		//-------------------                            SCORE STORAGE ARRAYLIST SETUP                                        ------------------
		//--------------------------------------------------------------------------------------------------------------------------------------		
		
		//ArrayList for pointTotal, totalCorrectAnswers, totalProblems
		java.util.List<Integer> scoreRecord = new ArrayList<Integer>();
		

        //--------------------------------------------------------------------------------------------------------------------------------------
        //--------------------                                   WELCOME & SETUP                                            --------------------
        //--------------------------------------------------------------------------------------------------------------------------------------
		
		
		//welcome message & prompt for username
		username = JOptionPane.showInputDialog("Hello!\n"
											+ "Welcome to Basic Math Practice!\n\n"
											+ "What's your name?");
		
		
//--------------------------------------------------------------------------------------------------------------------------------------
//------                                            CHOOSE TYPE OF PRACTICE                                          -------------------
//--------------------------------------------------------------------------------------------------------------------------------------
			
	
	while (playAgain.equalsIgnoreCase("Y") && (!mathType.equalsIgnoreCase("8"))) {
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
														+ "8. see high scores"
														+ "9. exit the game" );
			
			//if mathType is not valid, prompt again until a valid choice is made
			if (!mathType.equalsIgnoreCase("1") && !mathType.equalsIgnoreCase("2") && !mathType.equalsIgnoreCase("3") && !mathType.equalsIgnoreCase("4")
				&& !mathType.equalsIgnoreCase("5") && !mathType.equalsIgnoreCase("6") && !mathType.equalsIgnoreCase("7") && !mathType.equalsIgnoreCase("8")) { 
				do {
					mathType = JOptionPane.showInputDialog(null, "Oops! That option is not available.\n\nPlease choose a number 1-7:\n\n"
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
				}
				while (!mathType.equalsIgnoreCase("1") && !mathType.equalsIgnoreCase("2") && !mathType.equalsIgnoreCase("3") && !mathType.equalsIgnoreCase("4")
						&& !mathType.equalsIgnoreCase("5") && !mathType.equalsIgnoreCase("6") && !mathType.equalsIgnoreCase("7") && !mathType.equalsIgnoreCase("8"));
			}
			
			
			//-------------------------------------------------------------------------------------------------------------------------------------
			//------                                               CHOOSE CHALLENGE LEVEL                                   -----------------------
			//-------------------------------------------------------------------------------------------------------------------------------------
			
			
			//prompt for challenge level
				//Beginner (single digit)
				//Easy (double digits without carrying or borrowing)
				//Medium (double digit with carrying/borrowing)
				//Hard (up to 4 digits with carrying/borrowing)
				//Custom (set your own conditions)
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
			if (!challengeLevel.equalsIgnoreCase("1") && !challengeLevel.equalsIgnoreCase("2") && !challengeLevel.equalsIgnoreCase("3") && !challengeLevel.equalsIgnoreCase("4")
					&& !challengeLevel.equalsIgnoreCase("5")) { 
				do {
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
				}
				while (!challengeLevel.equalsIgnoreCase("1") && !challengeLevel.equalsIgnoreCase("2") && !challengeLevel.equalsIgnoreCase("3") && !challengeLevel.equalsIgnoreCase("4")
						&& !challengeLevel.equalsIgnoreCase("5"));
			}
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			///////                         Input for Challenge Level 5: Custom                       //////////
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			if (challengeLevel.equals("5")) {
				
				//prompt user for min/max numbers for 1st/2nd digits (call minMax function)
				min1stDigit = minDigit();
				max1stDigit = maxDigit();
				min2ndDigit = minDigit();
				max2ndDigit = maxDigit();
					
				//if mathType=1,2,5, or 7: prompt user: do you want to include problems with carrying & borrowing?
				if (mathType.equals("1") || mathType.equals("2") || mathType.equals("5") || mathType.equals("7")) {	
					while ((!carryBorrow.equalsIgnoreCase("y")) && (!carryBorrow.equalsIgnoreCase("n"))) {
						carryBorrow = JOptionPane.showInputDialog("Do you want to include problems that require carrying or borrowing?\n\n(Y)es or (N)o");
					}
				}
				
				//if mathType=4, 6, or 7: prompt user: do you want to include problems with remainders?
				if (mathType.equals("4") || mathType.equals("6") || mathType.equals("7")) {	
					while ((!allowRemainders.equalsIgnoreCase("y")) && (!allowRemainders.equalsIgnoreCase("n"))) {
						allowRemainders = JOptionPane.showInputDialog("Do you want to include problems with remainders?\n\n(Y)es or (N)o");
					}
				}
				
				//if allowRemainders is yes, should the remainders be expressed in whole numbers or decimals?
				if (allowRemainders.equalsIgnoreCase("y")) {
					while ((!wholeOrDecimal.equalsIgnoreCase("1")) && (!wholeOrDecimal.equalsIgnoreCase("2"))) {
						wholeOrDecimal = JOptionPane.showInputDialog("Should the remainders be in terms of:\n1) whole numbers (ex: r2) or\n2)decimals (ex: 1.25)?\n\n"
								+ "(please choose 1 or 2)");
					}
				}
				
				//if mathType=2, 5, or 7: prompt user: Is it ok if the correct answer is a negative number?
				if (mathType.equals("2") || mathType.equals("5") || mathType.equals("7")) {
					while ((!negativeAnswer.equalsIgnoreCase("y")) && (!negativeAnswer.equalsIgnoreCase("n"))) {
						negativeAnswer= JOptionPane.showInputDialog("Is it ok if the correct answer is a negative number?\n\n(Y)es or (N)o");
					}
				}
				
				//prompt user for score value for each correct answer (include option for using the timer)
				while (correctAnswerScoreInput=="") {
					try {
						correctAnswerScoreInput = JOptionPane.showInputDialog("How many points should each correct answer count for?\n\n(whole numbers only)");
						correctAnswerScore = Integer.parseInt(correctAnswerScoreInput);
					}
					catch(Exception e) {
						correctAnswerScoreInput="";
						JOptionPane.showMessageDialog(null,  "Oops!\n\nPlease enter only whole numbers.\n\nPress OK to try again.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				//prompt user if wrong answers should deduct points
				while (wrongAnswerScoreInput=="") {
					try {
						wrongAnswerScoreInput = JOptionPane.showInputDialog("Should wrong answers deduct points?\n\n(Y)es or (N)o");
						if (wrongAnswerScoreInput.equalsIgnoreCase("n")) {wrongAnswerScore=0;}//set wrongAnswerScore to 0 to simplify code if user chooses no
						if ((!wrongAnswerScoreInput.equalsIgnoreCase("Y")) && (!wrongAnswerScoreInput.equalsIgnoreCase("N"))){
							wrongAnswerScoreInput="";
							JOptionPane.showMessageDialog(null, "Oops!\n\nPlease choose only Y or N.\n\nPress OK to re-enter.", "Oops", JOptionPane.INFORMATION_MESSAGE);
						}
					}//close try
					catch(Exception e){
						wrongAnswerScoreInput="";
						JOptionPane.showMessageDialog(null, "Oops!\n\nPlease choose only Y or N.\n\nPress OK to re-enter.", "Oops", JOptionPane.INFORMATION_MESSAGE);
					}//close catch
				}//close wrong Answer Score Input block
				
				if (wrongAnswerScoreInput.equalsIgnoreCase("Y")) {
					try {
						wrongAnswerScoreInput = JOptionPane.showInputDialog("How many points should be deducated for each wrong answer?\n\n(whole numbers only)");
						wrongAnswerScore = Integer.parseInt(wrongAnswerScoreInput);
					}//close try
					catch (Exception e){
						wrongAnswerScoreInput="";
						JOptionPane.showMessageDialog(null,  "Oops!\n\nPlease enter only whole numbers.\nno decimals, commas, letters, etc.\n\nPress OK to try again.", 
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
					}//close catch
				}//close Wrong Answer Score input block
			}//close challenge level setup block
			
			
			//------------------------------------------------------------------------------------------------------------------------------------
			//---------------                                    CHOOSE TIMER OPTIONS                                                  -----------
			//-----------------------------------------------------------------------------------------------------------------------------------
									/* block temporarily disabled until timers can be set up*/
			
			//prompt for timer (y/n) - choosing n will skip this section, leaving timerLength w/no value
			/*timerChoice = JOptionPane.showInputDialog("Would you like to set a time limit?\n\n(Y)es or (N)o\n");*/
			
			//verify that user chose y or n
			/*while (!timerChoice.equalsIgnoreCase("Y") && !timerChoice.equalsIgnoreCase("N")) {
				timerChoice = JOptionPane.showInputDialog(null, "Oops!\nThat option is not available.\n\n"
						+ "Please choose Y or N.\n\n"
						+ "Would you like to set a time limit?\n(Y)es or (N)o", 
						  "Oops!",JOptionPane.INFORMATION_MESSAGE);
			}	
			
			//if timerChoice=y, prompt for max time allowed
			/*if (timerChoice.equalsIgnoreCase("y")){
				timerLengthinput = JOptionPane.showInputDialog("How many seconds will be allowed per problem?\n"
						+ "(please enter a whole number)\n");
				timerLengthVerify = Double.parseDouble(timerLengthinput);
				if (timerLengthVerify%1>0) {
					do {
						timerLengthinput = JOptionPane.showInputDialog(null, "Oops! We can only accept whole numbers.\n\n"
								+ "Please enter the number of seconds that will be allowed per problem:\n", "Oops!",
								JOptionPane.INFORMATION_MESSAGE);
						timerLengthVerify = Double.parseDouble(timerLengthinput);					
						}					
					while (timerLengthVerify%1>0);
				}
				timerLength=(int)timerLengthVerify;
				}*/
	
			
			//------------------------------------------------------------------------------------------------------------------------------------
			//---------------                                    CHOOSE NUMBER OF PROBLEMS                                             -----------
			//------------------------------------------------------------------------------------------------------------------------------------
			
			//prompt user for a number of problems (or continuous play)
			numOfProblemsInput = JOptionPane.showInputDialog("How many problems do you want to practice?");
			numOfProblems = Integer.parseInt(numOfProblemsInput);
	
			
			//------------------------------------------------------------------------------------------------------------------------------------
			//------                                                      START OF GAME                                                     -------
			//-------------------------------------------------------------------------------------------------------------------------------------
			
			
			//switch-case for gameplay
			switch (mathType) {
			
			
			//--------------------------------------------------------------------------------------------------------------------------------------
			//------                                                       ADDITION                                                -------
			//---------------------------------------------------------------------------------------------------------------------------------------
			
			case ("1"):
				for (int x=1;x<=numOfProblems;x++) {
					if (challengeLevel.equals("1")) {
						//generate random numbers, single digit
						firstDigit = randNumGen1(0,10, firstDigit, challengeLevel, mathType);
						secondDigit=randNumGen2(0,10, secondDigit, challengeLevel, mathType);
					}
					else if (challengeLevel.equals("2")) {
						//generate random numbers 10-89, without carrying when added
						do {
							firstDigit = randNumGen1(10,90, firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,90, secondDigit, challengeLevel, mathType);
							noCarrying = verifyNoCarry(firstDigit, secondDigit);
						}
						while (noCarrying==false);
					}
					else if (challengeLevel.equals("3")) {
						//generate random numbers 0-99
						firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
					}
					else if (challengeLevel.equals("4")) {
						//generate random numbers 2-4 digits each
						firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
					}
					else { //if challengeLevel=5, per challenge input
						firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
					}
					
					//calculate answer
					correctAnswerDbl = calculateAnswerAdd(firstDigit, secondDigit);
					correctAnswer = (int)correctAnswerDbl;
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					userGuess=(int)userGuessDbl;
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (userGuess==correctAnswer) {
						//points:
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close challengeLevel!=5 block
					}//close if answer is correct section	
				
					//if answer is wrong
					if (userGuess!=correctAnswer) {
						//show correct answer with a good try message
						totalProblems++;
						JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
							+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
							+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
					} //close bracket for (if userguess does not equal correctAnswer) block
					//}//close bracket for start of Beginner Level when if statement is used for gameplay
					
					
			} //close beginner play for loop
			break;
				
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                         SUBTRACTION                                                    -----------
			//------------------------------------------------------------------------------------------------------------------------------------------
			case ("2"):
				for (int x=1;x<=numOfProblems;x++) {
					if (challengeLevel.equals("1")) {
						//generate random numbers, single digit, no negative answers
						do {
							firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,10,secondDigit, challengeLevel, mathType);
							noNegatives = verifyNoNegatives(firstDigit, secondDigit);
						}
						while (noNegatives==false);
					}
					else if (challengeLevel.equals("2")) {
						//generate random numbers 10-89, without borrowing or negatives when added
						do {
							firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
							noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
						}
						while (noNegatives==false || noBorrowing==false);
					}
					else if (challengeLevel.equals("3")) {
						//generate random numbers 0-99, no negatives, borrowing permitted
						do {
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
							noNegatives = verifyNoNegatives(firstDigit, secondDigit);
						}
						while (noNegatives==false);
					}
					else if (challengeLevel.equals("4")) {
						//generate random numbers 2-4 digits each, borrowing & negatives permitted
						firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
					}
					else { //if challengeLevel=5, per challenge input
						//generate random digits, based on user input, no borrowing or negative answers allowed
						if (carryBorrow.equalsIgnoreCase("n") && negativeAnswer.equalsIgnoreCase("n")) {
							do {
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}
							while (noBorrowing==false || noNegatives==false);
						}
						
						//generate random digits, based on user input, negative answers allowed, but no borrowing
						else if (carryBorrow.equalsIgnoreCase("n")) {
							do {
								firstDigit = randNumGen1(min1stDigit,(max2ndDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min1stDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
							}
							while (noBorrowing==false);
						}
						
						//generate random digits, based on user input, borrowing allowed, no negative answers allowed
						else if (negativeAnswer.equalsIgnoreCase("n")) {
							do {
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}
							while (noNegatives==false);
						}
						
						//generate random digits, based on user input, negatives and borrowing allowed
						else {
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}
					}//close challenge level 5 number generator block
					
					//calculate answer
					correctAnswerDbl = calculateAnswerSub(firstDigit, secondDigit);
					correctAnswer = (int)correctAnswerDbl;
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("5")){
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//close if challengeLevel=5 block
					
					else {	//challengeLevel!=5
						if (userGuess==correctAnswer) {
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close challengeLevel!=5 block
					}//close if answer is correct section	
				
					//if answer is wrong
					if (userGuess!=correctAnswer) {
						//show correct answer with a good try message
						totalProblems++;
						JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
							+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
							+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
					} //close bracket for (if userguess does not equal correctAnswer) block
				}//close subtraction gameplay for loop
			
			//break
			break;
		
		
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                 MULTIPLICATION                                                             -------
			//------------------------------------------------------------------------------------------------------------------------------------------	
			case ("3"):
				for (int x=1;x<=numOfProblems;x++) {
					if (challengeLevel.equals("1")) { //only 1,2,5,10,11
						//generate random numbers, 1, 2, 5, 10, and 11 sets only
						while (firstDigit!=1 && firstDigit!=2 && firstDigit!=5 && firstDigit!=10 && firstDigit!=11) {
							firstDigit = randNumGen1(1,12,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,12,secondDigit, challengeLevel, mathType);
						}
					}
					else if (challengeLevel.equals("2")) {//full 1-12 table
						//generate random numbers 1-12
						firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
					}
					else if (challengeLevel.equals("3")) {
						//generate random numbers 0-99
						firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
					}
					else if (challengeLevel.equals("4")) {
						//generate random numbers 2-4 digits each
						firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
						secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
					}
					else { //if challengeLevel=5, per challenge input
						//generate random digits, based on user input
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}
					
					//calculate answer
					correctAnswerDbl = calculateAnswerMult(firstDigit, secondDigit);
					correctAnswer = (int)correctAnswerDbl;
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("5")){
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//close if challengeLevel=5 block
					
					else {	//challengeLevel!=5
							if (userGuess==correctAnswer) {
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) blockf
					}//close challengeLevel!=5 section
					
					//reset firstDigit to allow for another random number to be generated
					firstDigit=0;
				}//close multiplication gameplay for loop
			
			//break
			break;
		
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                 DIVISION                                                                   -------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			case ("4"):
				for (int x=1;x<=numOfProblems;x++) {
					if (challengeLevel.equals("1")) {//multiplication table, no remainders
						//generate random numbers
						do {
							firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
							noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
						}
						while (noRemainders==false);
					}
					else if (challengeLevel.equals("2")) {//:2 digits divided by 1 digit, no remainders
						//generate random numbers
						do {
							firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,10,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
							noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
						}
						while (noRemainders==false);
					}
					else if (challengeLevel.equals("3")) {//2-3 digits divided by 1-2 digits, with remainders (written as whole numbers)
						//generate random numbers
							firstDigit = randNumGen1(10,1000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,100,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
					}
					else if (challengeLevel.equals("4")) {//1-4 digits for each number, with remainders (written as decimals)
						//generate random numbers 1-4 digits each
						do { 
							firstDigit = randNumGen1(1,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,10000,secondDigit, challengeLevel, mathType);
							firstDigitDbl = Double.valueOf(firstDigit);
							secondDigitDbl = Double.valueOf(secondDigit);
						}
						while (firstDigitDbl/secondDigitDbl<.01);//prevents answers <.01
					}
					else { //if challengeLevel=5, use challenge setup input 
						//generate random digits, based on user input, no remainders allowed
						if (allowRemainders.equalsIgnoreCase("n")) {
							do {
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);	
							}
							while (noRemainders==false || firstDigitDbl/secondDigitDbl<.01);
						}
						
						//generate random digits, based on user input, remainders allowed
						else {
							//generate random digits, based on user input, remainders allowed
							do {
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
							}
							while (firstDigitDbl/secondDigitDbl<.01);
						}//close generate random digits w/o remainders
					}//close challenge level 5 number generator block
					
					//calculate answer
					if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("1")) {//whole number remainders
						correctAnswerDivPart1 = calculateAnswerDivPart1(firstDigit, secondDigit);
						correctAnswerDivPart2Dbl = calculateAnswerDivPart2Whole(firstDigit, secondDigit);
						correctAnswerDivPart2 = (int)correctAnswerDivPart2Dbl;
						
					}
					
					else if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("2")) {//decimal number remainders
						correctAnswerDivPart1 = calculateAnswerDivPart1(firstDigit, secondDigit);
						correctAnswerDivPart2Dbl = calculateAnswerDivPart2Dec(firstDigit, secondDigit);
						correctAnswerDbl = Double.valueOf(correctAnswerDivPart2Dbl) + correctAnswerDivPart2Dbl;
					}
					
					else { //no remainders
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
					}
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("1")) {//whole number remainders
						//open scanner object
						Scanner remainderAnswer = new Scanner(System.in);
						
						while (userGuessInput.equals("")){
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
						
							try {
								//change part1 & part2replaced to integers
								wholeNumberAnswer = Integer.parseInt(wholeNumbersEntered);
								remainderNumberAnswer = Integer.parseInt(remainderEntered);
							}
							catch(Exception e) {
								userGuessInput="";
								JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nEnter only numbers (no commas), a space, then R, then the remainder amount.\nEx: 12 R3"
										+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					
					else if (allowRemainders.equalsIgnoreCase("y") && wholeOrDecimal.equalsIgnoreCase("2")) {//decimal number remainders
						//open scanner object
						Scanner remainderAnswerReader = new Scanner(System.in);
						
						while (userGuessInput.equals("")){
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
						
							try {
								//change part1 & part2replaced to integers
								userGuessPart1 = Integer.parseInt(wholeNumbersEntered);
								userGuessRemainder = remainder/100.0;	
							}
							catch(Exception e) {
								userGuessInput="";
								JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nEnter only numbers (no commas), a space, then R, then the remainder amount.\nEx: 12 R3"
										+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
							}
							userGuessDbl=userGuessPart1 + userGuessRemainder;
						}
					}
					
					else {//no remainders
					userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
					}
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if challenge level = 5 **********************************************************************************************************INCOMPLETE SECTION
					
					
					if (challengeLevel.equals("5")){

						//correct with no remainders or decimal remainders
						if (userGuessDbl==correctAnswerDbl && (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2"))) {
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + pointTotal, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close correct with no remainders or decimal remainders block
						
						//correct with whole number remainders
						else if ((wholeNumberAnswer==correctAnswerDivPart1) && (remainderNumberAnswer==correctAnswerDivPart2) && (wholeOrDecimal.equals("1"))) {
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + wholeNumbersEntered + " R" + remainderEntered 
									+ "\n\nTotal points: " + pointTotal, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}
						
						//wrong with no remainders or decimal remainders
						totalProblems++;
						if  (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2")) {
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
								+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
								+ "\n\nTotal points: " + pointTotal, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						}
						
						//wrong with whole number remainders
						else {
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + wholeNumbersEntered + " R" + remainderEntered + "\n\n"
									+ "\n\nTotal points: " + pointTotal, "Incorrect", JOptionPane.INFORMATION_MESSAGE);	
						}
					}//close if challengeLevel=5 block
					
					//if challenge level != 5
					else {
						//correct answer
						
						//correct with no remainders or with decimal remainders
						if (userGuessDbl==correctAnswerDbl && (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2"))) {	
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if answer is correct section	
						
						//correct with whole number remainders
						else if ((wholeNumberAnswer==correctAnswerDivPart1) && (remainderNumberAnswer==correctAnswerDivPart2) && (wholeOrDecimal.equals("1"))) {
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + wholeNumbersEntered + " R" + remainderEntered 
									+ "\n\nTotal points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}

						//if answer is wrong
						else{
							
							//with no remainders or with decimal remainders
							totalProblems++;
							if  (allowRemainders.equalsIgnoreCase("n")||wholeOrDecimal.equals("2")) {
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
									+ "\n\nTotal points: " + pointTotal, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}
							
							//with whole number remainders
							else {
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + wholeNumbersEntered + " R" + remainderEntered + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							}
						} //close bracket for (if userguess does not equal correctAnswer) block
					}//close if challengeLevel!=5 block
				}//close division gameplay for loop
						
			
			//break
			break;
		
			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                        ADDITION & SUBTRACTION                                                             -------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			case ("5"):
			
				for (int x=1;x<=numOfProblems;x++) {
					//choose whether to do addition or subtraction (random number generator 1=add or 2=sub)
					chooseAddorSub = randNumGen1(1,3,firstDigit, challengeLevel, mathType);
					chooseAddorSubStr = Integer.toString(chooseAddorSub);
					
					if (chooseAddorSub==1) {
						//generate random digits to use
						if (challengeLevel.equals("1")) {
							//generate random numbers, single digit
							firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
							secondDigit=randNumGen2(0,10,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("2")) {
							//generate random numbers 10-89, without carrying when added
							do {
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noCarrying = verifyNoCarry(firstDigit, secondDigit);
							}
							while (noCarrying==false);
						}
						else if (challengeLevel.equals("3")) {
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("4")) {
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}
						else { //if challengeLevel=5, per challenge input
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}
						
						//calculate answer
						correctAnswerDbl = calculateAnswerAdd(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						userGuessDbl = answerPrompt(chooseAddorSubStr, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							if (userGuess==correctAnswer) {
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) block
						}//close if challengeLevel!=5 block
					
					}//close addition random number generator & answerPrompt blocks
					
					//if chooseAdorSub is 2 (subtraction)
					else if (chooseAddorSub==2){
						if (challengeLevel.equals("1")) {
							//generate random numbers, single digit, no negative answers
							do {
								firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,10,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}
							while (noNegatives==false);
						}
						else if (challengeLevel.equals("2")) {
							//generate random numbers 10-89, without borrowing or negatives when added
							do {
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
							}
							while (noNegatives==false || noBorrowing==false);
						}
						else if (challengeLevel.equals("3")) {
							//generate random numbers 0-99, no negatives, borrowing permitted
							do {
								firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}
							while (noNegatives==false);
						}
						else if (challengeLevel.equals("4")) {
							//generate random numbers 2-4 digits each, borrowing & negatives permitted
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}
						else { //if challengeLevel=5, per challenge input
							//generate random digits, based on user input, no borrowing or negative answers allowed
							if (carryBorrow.equalsIgnoreCase("n") && negativeAnswer.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}
								while (noBorrowing==false || noNegatives==false);
							}
							
							//generate random digits, based on user input, negative answers allowed, but no borrowing
							else if (carryBorrow.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
								}
								while (noBorrowing==false);
							}
							
							//generate random digits, based on user input, borrowing allowed, no negative answers allowed
							else if (negativeAnswer.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}
								while (noNegatives==false);
							}
							
							//generate random digits, based on user input, negatives and borrowing allowed
							else {
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}
						}//close challenge level 5 random number generator
					
					//calculate answer
					correctAnswer = calculateAnswerSub(firstDigit, secondDigit);
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					userGuessDbl = answerPrompt(chooseAddorSubStr, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("5")){
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//close if challengeLevel=5 block
					
					else {	//challengeLevel!=5
						if (userGuess==correctAnswer) {
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "-" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if answer is correct section	
					
						//if answer is wrong
						if (userGuess!=correctAnswer) {
							//show correct answer with a good try message
							totalProblems++;
							pointTotal-=wrongAnswerScore;
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
								+ firstDigit + " - " + secondDigit + " = " + correctAnswer + "\n\n"
								+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						} //close bracket for (if userguess does not equal correctAnswer) block
					}//close if challengeLevel!=5 block
				}//close subtraction random number generator and answerprompt blocks
					
				
			} //close addition/subtraction play for loop
			
			//break
			break;			

			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                        MULTIPLICATION & DIVISION                                                           -------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			case("6"):
				
				for (int x=1;x<=numOfProblems;x++) {
					//choose whether to do multiplication or division (random number generator 1=add or 2=sub)
					Random multOrDiv = new Random();
					chooseMultorDiv = multOrDiv.nextInt(1,3);
					chooseMultorDivStr = Integer.toString(chooseMultorDiv);
					
					if (chooseMultorDiv==1) {
						mathType.equals("3"); //reassigned to allow randNumGen1/randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("1")) { //only 1,2,5,10,11
							//generate random numbers, 1, 2, 5, 10, and 11 sets only
							while (firstDigit!=1 && firstDigit!=2 && firstDigit!=5 && firstDigit!=10 && firstDigit!=11) {
								firstDigit = randNumGen1(1,12,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,12,secondDigit, challengeLevel, mathType);
							}
						}
						else if (challengeLevel.equals("2")) {//full 1-12 table
							//generate random numbers 1-12
							firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("3")) {
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("4")) {
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}
						else { //if challengeLevel=5, per challenge input
							//generate random digits, based on user input
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}
						
						//calculate answer
						correctAnswerDbl = calculateAnswerMult(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
								if (userGuess==correctAnswer) {
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//close if answer is correct section	
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								} //close bracket for (if userguess does not equal correctAnswer) blockf
						}//close challengeLevel!=5 section
						
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
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							if (userGuess==correctAnswer) {
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {
								//show correct answer with a good try message
								totalProblems++;
								pointTotal-=wrongAnswerScore;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) block
						}//close if challengeLevel!=5 block
					}//close multiplication random number generator and answerprompt blocks
					
					//if division is chosen
					if (chooseMultorDiv==2) {
						mathType.equals("4"); //reassigned to allow randNumGen1/randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("1")) {//multiplication table, no remainders
							//generate random numbers
							do {
								firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}
							while (noRemainders==false);
						}
						else if (challengeLevel.equals("2")) {//:2 digits divided by 1 digit, no remainders
							//generate random numbers
							do {
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}
							while (noRemainders==false);
						}
						else if (challengeLevel.equals("3")) {//2-3 digits divided by 1-2 digits, with remainders (written as whole numbers)
							//generate random numbers
								firstDigit = randNumGen1(10,1000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,100,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
						}
						else if (challengeLevel.equals("4")) {//1-4 digits for each number, with remainders (written as decimals)
							//generate random numbers 1-4 digits each
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							//do { 
								firstDigit = randNumGen1(1,10000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10000,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
							//}
							//while (firstDigit%secondDigit<.01);//prevents most answers less than .01
						}
						else { //if challengeLevel=5, use challenge setup input 
							//generate random digits, based on user input, no remainders allowed
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							if (allowRemainders.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
									noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);	
								}
								while (noRemainders==false);
							}
							
							//generate random digits, based on user input, remainders allowed
							else {
								//generate random digits, based on user input, remainders allowed
								//set up a do/while to filter out answers that are less than .1****************************************************INCOMPLETE SECTION
								//do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
								}
								//while ();
							}//close challenge level 5 number generator block
						
						//calculate answer
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
								if (userGuess==correctAnswer) {	
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//close if answer is correct section	
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " / " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								} //close bracket for (if userguess does not equal correctAnswer) blockf
						}//close challengeLevel!=5 section
						
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
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							if (userGuessDbl==correctAnswerDbl) {
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuessDbl!=correctAnswerDbl) {
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) block
						}//close if challengeLevel!=5 block
					}//close division random number generator and answerprompt blocks
					
					
				} //close multiplication/division play for loop
		
			//break
			break;			

			
			//-----------------------------------------------------------------------------------------------------------------------------------------
			//------                                                 ALL                                                                        -------
			//------------------------------------------------------------------------------------------------------------------------------------------			
			case("7"):
			//use a number generator to choose whether to do add, sub, mult, or div
				for (int x=1;x<=numOfProblems;x++) {
					//choose whether to do addition or subtraction (random number generator 1=add or 2=sub)
					Random randomAddSubMultDiv = new Random();
					chooseAddSubMultDiv = randomAddSubMultDiv.nextInt(1,5);
					chooseAddSubMultDivStr = Integer.toString(chooseAddSubMultDiv);
					
					if (chooseAddSubMultDiv==1) {
						mathType.equals("1"); //changed to allow randNumGen1,randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("1")) {
							//generate random numbers, single digit
							firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
							secondDigit=randNumGen2(0,10,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("2")) {
							//generate random numbers 10-89, without carrying when added
							do {
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noCarrying = verifyNoCarry(firstDigit, secondDigit);
							}
							while (noCarrying==false);
						}
						else if (challengeLevel.equals("3")) {
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("4")) {
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}
						else { //if challengeLevel=5, per challenge input
							firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
						}
						
						//calculate answer
						correctAnswerDbl = calculateAnswerAdd(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						userGuessDbl = answerPrompt(chooseAddSubMultDivStr, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							if (userGuess==correctAnswer) {
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " + " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) block
						}//close if challengeLevel!=5 block
					
					}//close addition random number generator & answerPrompt blocks
					
					//if chooseAdorSub is 2 (subtraction)
					else if (chooseAddSubMultDiv==2){
						mathType.equals("2");  //changed to allow randNumGen1,randNumGen2 functions to work
						if (challengeLevel.equals("1")) {
							//generate random numbers, single digit, no negative answers
							do {
								firstDigit = randNumGen1(0,10,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,10,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}
							while (noNegatives==false);
						}
						else if (challengeLevel.equals("2")) {
							//generate random numbers 10-89, without borrowing or negatives when added
							do {
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(10,90,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
							}
							while (noNegatives==false || noBorrowing==false);
						}
						else if (challengeLevel.equals("3")) {
							//generate random numbers 0-99, no negatives, borrowing permitted
							do {
								firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
								noNegatives = verifyNoNegatives(firstDigit, secondDigit);
							}
							while (noNegatives==false);
						}
						else if (challengeLevel.equals("4")) {
							//generate random numbers 2-4 digits each, borrowing & negatives permitted
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}
						else { //if challengeLevel=5, per challenge input
							//generate random digits, based on user input, no borrowing or negative answers allowed
							if (carryBorrow.equalsIgnoreCase("n") && negativeAnswer.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}
								while (noBorrowing==false || noNegatives==false);
							}
							
							//generate random digits, based on user input, negative answers allowed, but no borrowing
							else if (carryBorrow.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noBorrowing = verifyNoBorrowing(firstDigit, secondDigit);
								}
								while (noBorrowing==false);
							}
							
							//generate random digits, based on user input, borrowing allowed, no negative answers allowed
							else if (negativeAnswer.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									noNegatives = verifyNoNegatives(firstDigit, secondDigit);
								}
								while (noNegatives==false);
							}
							
							//generate random digits, based on user input, negatives and borrowing allowed
							else {
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}
						}//close challenge level 5 random number generator
					
					//calculate answer
					correctAnswer = calculateAnswerSub(firstDigit, secondDigit);
					
					//show problem, prompt for answer, start timer
					//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
					userGuessDbl = answerPrompt(chooseAddSubMultDivStr, firstDigit, secondDigit);
					userGuess = (int)userGuessDbl;
					//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
					//if answer is correct
					if (challengeLevel.equals("5")){
						//congrats message
						totalProblems++;
						totalCorrectAnswers++;
						pointTotal+=correctAnswerScore;
						JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "+" + secondDigit + "=" + correctAnswer + "\n\n"
								+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
					}//close if challengeLevel=5 block
					
					else {	//challengeLevel!=5
						if (userGuess==correctAnswer) {
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal++;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "-" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if answer is correct section	
					
						//if answer is wrong
						if (userGuess!=correctAnswer) {
							//show correct answer with a good try message
							totalProblems++;
							pointTotal-=wrongAnswerScore;
							JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
								+ firstDigit + " - " + secondDigit + " = " + correctAnswer + "\n\n"
								+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
						} //close bracket for (if userguess does not equal correctAnswer) block
					}//close if challengeLevel!=5 block
				}//close subtraction random number generator and answerprompt blocks
					
					//if multiplication is chosen
					if (chooseAddSubMultDiv==3) {
						mathType.equals("3");  //changed to allow randNumGen1,randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("1")) { //only 1,2,5,10,11
							//generate random numbers, 1, 2, 5, 10, and 11 sets only
							while (firstDigit!=1 && firstDigit!=2 && firstDigit!=5 && firstDigit!=10 && firstDigit!=11) {
								firstDigit = randNumGen1(1,12,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,12,secondDigit, challengeLevel, mathType);
							}
						}
						else if (challengeLevel.equals("2")) {//full 1-12 table
							//generate random numbers 1-12
							firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("3")) {
							//generate random numbers 0-99
							firstDigit = randNumGen1(0,100,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(0,100,secondDigit, challengeLevel, mathType);
						}
						else if (challengeLevel.equals("4")) {
							//generate random numbers 2-4 digits each
							firstDigit = randNumGen1(10,10000,firstDigit, challengeLevel, mathType);
							secondDigit = randNumGen2(10,10000,secondDigit, challengeLevel, mathType);
						}
						else { //if challengeLevel=5, per challenge input
							//generate random digits, based on user input
								firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
							}
						
						//calculate answer
						correctAnswerDbl = calculateAnswerMult(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
								if (userGuess==correctAnswer) {
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//close if answer is correct section	
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								} //close bracket for (if userguess does not equal correctAnswer) blockf
						}//close challengeLevel!=5 section
						
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
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							if (userGuess==correctAnswer) {
								//points:
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "x" + secondDigit + "=" + correctAnswer + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuess!=correctAnswer) {
								//show correct answer with a good try message
								totalProblems++;
								pointTotal-=wrongAnswerScore;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " x " + secondDigit + " = " + correctAnswer + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) block
						}//close if challengeLevel!=5 block
					}//close multiplication random number generator and answerprompt blocks
					
					//if division is chosen
					if (chooseAddSubMultDiv==4) {
						mathType.equals("4");  //changed to allow randNumGen1,randNumGen2 functions to work
						//generate random digits to use
						if (challengeLevel.equals("1")) {//multiplication table, no remainders
							//generate random numbers
							do {
								firstDigit = randNumGen1(1,13,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,13,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}
							while (noRemainders==false);
						}
						else if (challengeLevel.equals("2")) {//:2 digits divided by 1 digit, no remainders
							//generate random numbers
							do {
								firstDigit = randNumGen1(10,90,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
								noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);
							}
							while (noRemainders==false);
						}
						else if (challengeLevel.equals("3")) {//2-3 digits divided by 1-2 digits, with remainders (written as whole numbers)
							//generate random numbers
								firstDigit = randNumGen1(10,1000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,100,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
						}
						else if (challengeLevel.equals("4")) {//1-4 digits for each number, with remainders (written as decimals)
							//generate random numbers 1-4 digits each
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							//do { 
								firstDigit = randNumGen1(1,10000,firstDigit, challengeLevel, mathType);
								secondDigit = randNumGen2(1,10000,secondDigit, challengeLevel, mathType);
								firstDigitDbl = Double.valueOf(firstDigit);
								secondDigitDbl = Double.valueOf(secondDigit);
							//}
							//while (firstDigit%secondDigit<.01);//prevents most answers less than .01
						}
						else { //if challengeLevel=5, use challenge setup input 
							//generate random digits, based on user input, no remainders allowed
							//set up a do/while to filter out answers that are less than .1*****************************************************INCOMPLETE SECTION
							if (allowRemainders.equalsIgnoreCase("n")) {
								do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
									noRemainders = verifyNoRemainders(firstDigitDbl, secondDigitDbl);	
								}
								while (noRemainders==false);
							}
							
							//generate random digits, based on user input, remainders allowed
							else {
								//generate random digits, based on user input, remainders allowed
								//set up a do/while to filter out answers that are less than .1****************************************************INCOMPLETE SECTION
								//do {
									firstDigit = randNumGen1(min1stDigit,(max1stDigit+1),firstDigit, challengeLevel, mathType);
									secondDigit = randNumGen2(min2ndDigit,(max2ndDigit+1),secondDigit, challengeLevel, mathType);
									firstDigitDbl = Double.valueOf(firstDigit);
									secondDigitDbl = Double.valueOf(secondDigit);
								}
								//while ();
							}//close challenge level 5 number generator block
						
						//calculate answer
						correctAnswerDbl = calculateAnswerDiv(firstDigit, secondDigit);
						correctAnswer = (int)correctAnswerDbl;
						
						//show problem, prompt for answer, start timer
						//*****TIMER START NOT WRITTEN IN YET ********************************************************************************************INCOMPLETE SECTION			
						userGuessDbl = answerPrompt(mathType, firstDigit, secondDigit);
						userGuess = (int)userGuessDbl;
						//*****STOP TIMER HERE*************************************************************************************************************INCOMPLETE SECTION
					
						//if answer is correct
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
								if (userGuess==correctAnswer) {
									//congrats message
									totalProblems++;
									totalCorrectAnswers++;
									pointTotal++;
									JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswer + "\n\n"
											+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
								}//close if answer is correct section	
							
								//if answer is wrong
								if (userGuess!=correctAnswer) {
									//show correct answer with a good try message
									totalProblems++;
									JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
										+ firstDigit + " / " + secondDigit + " = " + correctAnswer + "\n\n"
										+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
								} //close bracket for (if userguess does not equal correctAnswer) blockf
						}//close challengeLevel!=5 section
						
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
						if (challengeLevel.equals("5")){
							//congrats message
							totalProblems++;
							totalCorrectAnswers++;
							pointTotal+=correctAnswerScore;
							JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
									+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
						}//close if challengeLevel=5 block
						
						else {	//challengeLevel!=5
							if (userGuessDbl==correctAnswerDbl) {
								//congrats message
								totalProblems++;
								totalCorrectAnswers++;
								pointTotal++;
								JOptionPane.showMessageDialog(null, "Yes!\nThat is correct.\n" + firstDigit + "/" + secondDigit + "=" + correctAnswerDbl + "\n\n"
										+ "Total points: " + totalCorrectAnswers, "Correct!", JOptionPane.INFORMATION_MESSAGE);
							}//close if answer is correct section	
						
							//if answer is wrong
							if (userGuessDbl!=correctAnswerDbl) {
								//show correct answer with a good try message
								totalProblems++;
								JOptionPane.showMessageDialog(null, "Good try, but that is incorrect.\n\n" 
									+ firstDigit + " / " + secondDigit + " = " + correctAnswerDbl + "\n\n"
									+ "\n\nTotal points: " + totalCorrectAnswers, "Incorrect", JOptionPane.INFORMATION_MESSAGE);
							} //close bracket for (if userguess does not equal correctAnswer) block
						}//close if challengeLevel!=5 block
					}//close division random number generator and answerprompt blocks	
				
			} //close all play loop
			
			//break
			break;
			
			//see high scores
			case("8"):
				//show high scores file
				JOptionPane.showMessageDialog(null, "This option has not been set up yet.", "Coming soon", JOptionPane.WARNING_MESSAGE);
				
				line = null; //temp variable to get BufferedReader working
				
				try
		        {
		            FileReader highScoresReader = new FileReader("highscores.txt");
		            BufferedReader bufferedReaderForHighScores = new BufferedReader(highScoresReader);
		            
		            while((line = bufferedReaderForHighScores.readLine()) != null)
		                System.out.println(line);
		            
		            bufferedReaderForHighScores.close();
		        }
		        catch(IOException ex) {
		        	JOptionPane.showMessageDialog(null, "Uh oh!\nSomething went wrong.\n\n(exception: " + ex, "Error", JOptionPane.ERROR_MESSAGE, null);
		        }
			break;
	
		}//close switch-case for gameplay
			
			
	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	//-------------                                               DISPLAY SCORE & OFFER REPLAY                                               ----------------
	//-------------------------------------------------------------------------------------------------------------------------------------------------------
			
	
		//------------------------------------------------------------------------
		//------                  DISPLAY SCORE                              -----
		//------------------------------------------------------------------------
			
		finalScore = (Double.valueOf(totalCorrectAnswers))/(Double.valueOf(totalProblems));
		
		//store final scores (username, finalScore, totalCorrectAnswers, totalProblems)
		try {
			FileWriter highScoresFW = new FileWriter("highscores.txt");
			highScoresFW.write(username + "\n" + finalScore + "\n" + totalCorrectAnswers + "\n" + totalProblems);
			highScoresFW.close();
		}
		catch (Exception e){JOptionPane.showMessageDialog(null, "Something went wrong.", "uh oh", JOptionPane.ERROR_MESSAGE);}
		
		//display final scores
		if (finalScore==1) {
			JOptionPane.showMessageDialog(null, "PERFECT SCORE!\n\n You answered all " + totalCorrectAnswers + " problems correctly!");
		}
		else if (finalScore>=0.9 && finalScore<1) {	
			JOptionPane.showMessageDialog(null,"That was amazing!\n\nYou answered " + totalCorrectAnswers + " out of " + totalProblems + " problems correctly!");
		}
		else if (finalScore>0.5 && finalScore<0.9) {
			JOptionPane.showMessageDialog(null, "Good job!\n\nYou answered " + totalCorrectAnswers + " out of " + totalProblems + " problems correctly!");
		}
		else {
		JOptionPane.showMessageDialog(null, "Good try.\n\nYou answered " + totalCorrectAnswers + " out of " + totalProblems + " problems correctly!");
		} 
		
		
			
		//------------------------------------------------------------------------
		//------                  PLAY AGAIN?                                -----
		//------------------------------------------------------------------------
		
		//add a way to play again
		do {
			
			//prompt for play again? y/n
			playAgain = JOptionPane.showInputDialog("Do you want to play again?\n\n(Y)es or (N)o");
			//if invalid input is entered, display error message
			if ((!playAgain.equalsIgnoreCase("y")) && (!playAgain.equalsIgnoreCase("n"))) {
				JOptionPane.showMessageDialog(null, "Oops! Please enter a number.\n\nPress OK to return to the \"Play Again?\" menu", "Oops", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
//why is this section here?*********************************************************************************************************************************CHECK ON THIS
		while ((!playAgain.equalsIgnoreCase("y")) && (!playAgain.equalsIgnoreCase("n")));
			//store scores from current round in score array
			scoreRecord.add(pointTotal);
			scoreRecord.add(totalCorrectAnswers);
			scoreRecord.add(totalProblems);
			int finalScoreInt = (int)(finalScore*100);
			scoreRecord.add(finalScoreInt);
			
		
		if (playAgain.equalsIgnoreCase("Y")){
			//accumulate number of rounds
			roundCount++;
			
			//reset all accumulators
			JOptionPane.showMessageDialog(null, scoreRecord.get(0));
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
			}//close if accumulator reset section
	
	}//close play/repeat block			

	//show scoreRecord data
	scoreOutputCounter=0;
	do{
		
		scoreOutput = scoreOutput
			+ "\n\nRound " + (roundNumDisplayCounter) + ": " 
			+ "\n\tCorrect Answers: " + scoreRecord.get(scoreOutputCounter+1) 
			+ "\n\tTotal Problems: " + scoreRecord.get(scoreOutputCounter+2)
			+ "\n\tTotal Points: " + scoreRecord.get(scoreOutputCounter)
			+ "\n\tAccuracy: " + scoreRecord.get(scoreOutputCounter+3) + "%";
		overallScore += scoreRecord.get(scoreOutputCounter);
		scoreOutputCounter+=4;
		roundNumDisplayCounter++;
	}
	while (scoreOutputCounter<((roundCount*4)-1));
	
	JOptionPane.showMessageDialog(null, "Good game today!\n\nYour scores:" + scoreOutput
			+ "\n\nOverall Point Total: " + overallScore);

//add a high score file for each level, and for overall (based on percentage of questions answered correctly)

}//close main
	

	//--------------------------------------------------------------------------------------------------------------------------------------
	//------                                                      FUNCTIONS                                                -------
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	//prompt for answer input
	//verify that input is integer for add/sub/mult
	public static double answerPrompt(String mmc, int x, int y) {
		String userGuessInput="";
		double z=0;
		while (userGuessInput=="") {
			try {
				if (mmc.equals("1")) {
					userGuessInput = JOptionPane.showInputDialog(x + "+" + y + "=\n\nEnter numbers only.\n(no commas)");
					z = Double.parseDouble(userGuessInput);
				}
				if (mmc.equals("2")) {
					userGuessInput = JOptionPane.showInputDialog(x + "-" + y + "= \n\nOnly numbers and negative signs can be entered.\n(no commas)");
					z = Double.parseDouble(userGuessInput);
				}
				if (mmc.equals("3")) {
					userGuessInput = JOptionPane.showInputDialog(x + "x" + y + "=\n\nEnter numbers only.\n(no commas)");
					z = Double.parseDouble(userGuessInput);
				}
				if (mmc.equals("4")) {
					userGuessInput = JOptionPane.showInputDialog(x + "/" + y + "=");
					z = Double.parseDouble(userGuessInput);
				}
			}
			catch(Exception e) {
				userGuessInput="";
				JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nNo letters, commas, or other stuff will work.\n\n\n"
						+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
			}
		}//close userGuessInput section
		return z;
	}
	
	//prompt for userGuess for division, where whole number remainders are allowed
	public static int answerPromptDivRemaindersPart1(int x, int y) {
		//open scanner object
		Scanner remainderAnswer = new Scanner(System.in);
		
		int z=0;
		String userGuessInput="", a;
		
		while (userGuessInput.equals("")){
			try {
				//enter a number with remainder (format expected: 123 R4)
				userGuessInput = JOptionPane.showInputDialog("Answer format: 123 R4\n\n" + x + "/" + y + "=");
					
				//read first part of entry
				a = remainderAnswer.next();
				z = Integer.parseInt(a);
			}
			catch(Exception e) {
				userGuessInput="";
				JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nEnter only numbers (no commas), then R, then the remainder amount.\nEx: 12 R3"
						+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		remainderAnswer.close();
		return z;
	}
	
	//find whole number remainder for division problem
	public static int answerPromptDivRemaindersPart2(int x, int y) {
		double a = Double.valueOf(x);
		double b = Double.valueOf(y);
		double c = a%b;
		int z = (int)c;
		return z;
	}
	
	//calculate answer
	public static int calculateAnswerAdd (int x, int y) {
		return (x + y);
	}

	//calculate answer for division without remainders
	public static double calculateAnswerDiv(double x, double y) {
		double z = x/y;
		z = (Math.round(z*100.0))/100.0; //move decimal place 2 to the right, round last digit, move decimal back left 2 spots
		return z;
	}
	
	//calculate answer for division - whole number portion
	public static int calculateAnswerDivPart1(int x, int y) {
		return (x/y);
	}
	
	//calculate answer for division - decimal remainder portion
	public static double calculateAnswerDivPart2Dec(int x, int y) {
		double a = Double.valueOf(x);
		double b = Double.valueOf(y);
		double c = (a/b) - (int)(a/b);
		c = Math.round(c*100);
		double z = c/100;
		return z;
	}
	
	//calculate answer for division = whole number remainder portion
	public static int calculateAnswerDivPart2Whole(int x, int y) {
		double a = Double.valueOf(x);
		double b = Double.valueOf(y);
		double c = (a/b) - (int)(a/b);
		c = Math.round(c*100);
		int z = (int)c;	
		return z;
	}
		
	//calculate answer for subtraction
	public static int calculateAnswerSub(int x, int y) {
		return (x-y);
	}
	
	//calculate answer for multiplication
	public static int calculateAnswerMult(int x, int y) {
		return (x*y);
	}

	//prompt for minimum & maximum limits for 1st & 2nd digits for custom challenge level (5)
	public static int maxDigit() {
		int maxDigit=0;
		String minMaxInput="";
		try {
			minMaxInput = JOptionPane.showInputDialog("Welcome to the Custom Setup\nwhere you name the settings!\n\n"
			+ "What is the smallest number you want us to choose for the challenges?\n(whole positive numbers only)\n\n\n"
			+ "The \"first number\" is 3 in the following:\n3+1=4\n3-2=1\n3*2=6\n3/1=1.5\n");
			maxDigit = Integer.parseInt(minMaxInput);
		}
		catch(Exception e) {
			minMaxInput="";
			JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nDo not include letters, negatives, decimals, fractions, commas, etc.\n\n"
					+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
		}
		return maxDigit;
	}
		
	//prompt for minimum limits for 1st & 2nd digits for custom challenge level (5)
	public static int minDigit() {
		int minDigit=0;
		String minMaxInput="";
		try {
			minMaxInput = JOptionPane.showInputDialog("Welcome to the Custom Setup\nwhere you name the settings!\n\n"
			+ "What is the smallest number you want us to choose for the challenges?\n(whole positive numbers only)\n\n\n"
			+ "The \"first number\" is 3 in the following:\n3+1=4\n3-2=1\n3*2=6\n3/1=1.5\n");
			minDigit = Integer.parseInt(minMaxInput);
		}
		catch(Exception e) {
			minMaxInput="";
			JOptionPane.showMessageDialog(null,"Oops!\n\nRemember:\nOnly enter numbers.\n\nDo not include letters, negatives, decimals, fractions, commas, etc.\n\n"
					+ "Press OK to try again.", "Oops!",JOptionPane.INFORMATION_MESSAGE);
		}
		return minDigit;
	}
		
	//choose random number for firstDigit
	public static int randNumGen1 (int min, int max, int old1st, String challLevel, String typeOfMath) {
		boolean test=false;
		int z=0;
		
		//test to prevent repetitive random numbers being returned
		while (test==false){
			//open instance of Random class
			Random randomGen = new Random();
			
			//choose the random single digit
			z = randomGen.nextInt(min, max);

			//if challengeLevel=1
			if (challLevel.equals("1")) {
				//if (typeOfMath=1 or typeOfMath=2 or typeOfMath=5) & z<+/-2 from old1st, test=false
				if ((typeOfMath.equals("1") || typeOfMath.equals("2") || typeOfMath.equals("5")) && ((z>=old1st && (z-2)<old1st) || (z<=old1st && (z+2)>old1st ))){test=false;}
				//else if (typeOfMath=3 or typeOfMath=4 or typeOfMath=6) & z=old1st, test=false
				else if ((typeOfMath.equals("3") || typeOfMath.equals("4") || typeOfMath.equals("6")) && z==old1st) {test = false;}
				//else test=true
				else {test=true;}
			}
			
			//else if challengeLevel=2
			else if (challLevel.equals("2")) {
				//if (typeOfMath=1 or typeOfMath=2 or typeOfMath=5) & z<+/-5 from old1st, test=false
				if ((typeOfMath.equals("1") || typeOfMath.equals("2") || typeOfMath.equals("5")) && ((z>=old1st && (z-5)<old1st) || (z<=old1st && (z+5)>old1st ))) {test=false;}
				//else if (typeOfMath=3 & z=old1st, test=false
				else if (typeOfMath.equals("3") && z==old1st) {test=false;}
				//else if (typeOfMath=4 & z<+/-7 from old1st, test=false
				else if (typeOfMath.equals("4") && ((z>=old1st && (z-7)<old1st) || (z<=old1st && (z+7)>old1st ))){test=false;}
				//else test=true
				else {test=true;}
			}
			
			//else if challengeLevel=3
			else if (challLevel.equals("3")) {
				//if (typeOfMath=1 or typeOfMath=2 or typeOfMath=5) & z<+/-7 from old1st, test=false
				if ((typeOfMath.equals("1") || typeOfMath.equals("2") || typeOfMath.equals("5")) && ((z>=old1st && (z-7)<old1st) || (z<=old1st && (z+7)>old1st ))) {test=false;}
				//else if (typeOfMath=3 or typeOfMath=4 or typeOfMath=6) & z<+/-7 from old1st, test=false
				else if ((typeOfMath.equals("3") || typeOfMath.equals("4") || typeOfMath.equals("6")) && ((z>=old1st && (z-7)<old1st) || (z<=old1st && (z+7)>old1st ))) {test=false;}
				//else test=true
				else {test=true;}
			}
			
			//else if challengeLevel=4
			else if (challLevel.equals("4")) {
				//z<+/-123 from old1st, test=false
				if ((z>=old1st && (z-123)<old1st) || (z<=old1st && (z+123)>old1st)) {test=false;}
				//else test=true
				else {test=true;}
			}
			//else, test=true
			else {test=true;}
			
			//challengeLevel 5 not tested at all
		}//close while test=false (choose & retest loop)

		return z;
	}

	//choose random number for secondDigit
	public static int randNumGen2 (int min, int max, int old2nd, String challLevel, String typeOfMath) {
		boolean test=false;
		int z=0;
		
		//test to prevent repetitive random numbers being returned
		while (test==false){
			//open instance of Random class
			Random randomGen = new Random();
			
			//choose the random single digit
			z = randomGen.nextInt(min, max);

			//if challengeLevel=1
			if (challLevel.equals("1")) {
				//if (typeOfMath=1 or typeOfMath=2 or typeOfMath=5) & z<+/-2 from old2nd, test=false
				if ((typeOfMath.equals("1") || typeOfMath.equals("2") || typeOfMath.equals("5")) && ((z>=old2nd && (z-2)<old2nd) || (z<=old2nd && (z+2)>old2nd ))) 
					{test=false;}
				//else if typeOfMath=3 & z<+/-2, test=false
				else if (typeOfMath.equals("3") && (((z>=old2nd && (z-2)<old2nd) || (z<=old2nd && (z+2)>old2nd )))) {test=false;}
				//else if typeOfMath=4 & z=old2nd, test=false
				else if (typeOfMath.equals("4") && z==old2nd) {test=false;}
				//else test=true
				else {test=true;}
			}
			//else if challengeLevel=2
			else if (challLevel.equals("2")) {
				//typeOfMath=6 is validated through converting the mathType to 3 or 4 in main
				//if (typeOfMath=1 or typeOfMath=2 or typeOfMath=5) & z<+/-5 from old2nd, test=false
				if ((typeOfMath.equals("1") || typeOfMath.equals("2") || typeOfMath.equals("5")) && ((z>=old2nd && (z-5)<old2nd) || (z<=old2nd && (z+5)>old2nd ))) 
					{test=false;}
				//else if (typeOfMath=3 & z=old2nd, test=false
				else if (typeOfMath.equals("3") && z==old2nd) {test=false;}
				//else if (typeOfMath=4 & z<+/-7 from old2nd, test=false
				else if (typeOfMath.equals("4") && ((z>=old2nd && (z-7)<old2nd) || (z<=old2nd && (z+7)>old2nd ))) {test=false;}
				//else test=true
				else {test=true;}
			}
			
			//else if challengeLevel=3
			else if (challLevel.equals("3")) {
				//if (typeOfMath=1 or typeOfMath=2 or typeOfMath=5) & z<+/-7 from old2nd, test=false
				if ((typeOfMath.equals("1") || typeOfMath.equals("2") || typeOfMath.equals("5")) && ((z>=old2nd && (z-7)<old2nd) || (z<=old2nd && (z+7)>old2nd )))
					{test=false;}
				//else if (typeOfMath=3 or typeOfMath=4 or typeOfMath=6) & z<+/-7 from old2nd, test=false
				else if ((typeOfMath.equals("3") || typeOfMath.equals("4") || typeOfMath.equals("6")) && ((z>=old2nd && (z-7)<old2nd) || (z<=old2nd && (z+7)>old2nd )))
					{test=false;}
				//else test=true
				else {test=true;}
			}
			
			//else if challengeLevel=4
			else if (challLevel.equals("4")) {
				//z<+/-123 from old2nd, test=false
				if ((z>=old2nd && (z-123)<old2nd) || (z<=old2nd && (z+123)>old2nd )) {test=false;}
				//else test=true
				else {test=true;}
			}
			
			//else, test=true
			else {test=true;}
			
			//challengeLevel 5 not tested at all
		}//close while test=false (choose & retest loop)
		return z;
	}
	
	//verify subtraction problems have no borrowing needed
		public static boolean verifyNoBorrowing(int x, int y) {
			//going right to left, check if s-y<0 for any of them - if it is, return a z=false
	        boolean z=true;
	        String stringFirstDigit = Integer.toString(x); //firstDigit to string
			int num1 = Integer.parseInt(stringFirstDigit);
			do {
				int lastDigitx = num1 % 10;
				num1=num1/10;
				int lastDigity = y % 10;
				y=y/10;
				if (lastDigitx-lastDigity<0) {z=false;}
			}//close do
			while (num1>0);
			return z;
		}
		
	//verify addition problems have no carrying
	public static boolean verifyNoCarry(int x, int y) {
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
		if (firstDigitOnesPlace+secondDigitOnesPlace<=9 || firstDigitTensPlace+secondDigitTensPlace<=9) {z=true;}
		return z;
	}
	
	//verify subtraction problems have no negative answers
		public static boolean verifyNoNegatives(int x, int y) {
			boolean z=false;
			if (x-y>=0) {z=true;}
			return z;
		}
		
	//verify no remainders for division
	public static boolean verifyNoRemainders(double x, double y) {
		boolean z=true;
		double test = x%y;
		if (test!=0) {z=false;}
		return z;
	}
	
}//close class