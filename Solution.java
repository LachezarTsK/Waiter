import java.util.Scanner;
import java.util.Stack;

public class Solution {

	private static int getNextPrime(int nextBaseToStartSearchForPrime) {
		int nextPrime;
		if (nextBaseToStartSearchForPrime == 0) {
			nextPrime = 2;
			return nextPrime;
		}
		if (nextBaseToStartSearchForPrime == 2) {
			nextPrime = 3;
			return nextPrime;
		}

		int numberToCheckForPime = nextBaseToStartSearchForPrime + 2;
		while (true) {
			boolean isPrime = true;
			for (int i = 3; i < numberToCheckForPime / 2; i += 2) {
				if (numberToCheckForPime % i == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				return numberToCheckForPime;
			}
			numberToCheckForPime += 2;
		}
	}

	private static void getResults(int numberOfIterations, Stack<Integer> initailStack) {
		int nextBaseToStartSearchForPrime = 0;

		for (int i = 0; i < numberOfIterations; i++) {
			Stack<Integer> stackRemaining = new Stack<Integer>();
			Stack<Integer> stackPrimes = new Stack<Integer>();
			int currentPrime = getNextPrime(nextBaseToStartSearchForPrime);
			nextBaseToStartSearchForPrime = currentPrime;

			while (!initailStack.isEmpty()) {
				int currentNumber = initailStack.pop();
				if (currentNumber % currentPrime == 0) {
					stackPrimes.push(currentNumber);
				} else {
					stackRemaining.push(currentNumber);
				}
			}
			printResults(stackPrimes);
			initailStack.addAll(stackRemaining);
		}
		printResults(initailStack);
	}

	private static void printResults(Stack<Integer> stack) {
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfPlates = scanner.nextInt();
		int numberOfIterations = scanner.nextInt();
		Stack<Integer> initailStack = new Stack<Integer>();

		for (int i = 0; i < numberOfPlates; i++) {
			initailStack.push(scanner.nextInt());
		}
		scanner.close();
		getResults(numberOfIterations, initailStack);
	}
}
