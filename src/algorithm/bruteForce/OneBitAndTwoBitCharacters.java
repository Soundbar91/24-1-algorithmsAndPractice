package algorithm.bruteForce;

public class OneBitAndTwoBitCharacters {

    public static boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            if (bits[i] == 1) {
                i += 2;
            } else {
                i++;
            }
        }
        return i == bits.length - 1;
    }

    public static void main(String[] args) {
        int[] bits1 = {1, 0, 0};
        int[] bits2 = {1, 1, 1, 0};
        System.out.println(isOneBitCharacter(bits1)); // Output: true
        System.out.println(isOneBitCharacter(bits2)); // Output: false
    }
}
