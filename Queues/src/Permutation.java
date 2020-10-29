public class Permutation {
    public static void main(String[] args) {
        int permutationCount = Integer.parseInt(args[0]);
        int argumentCount = args.length;
        // Adds Elements to the RNGQueue
        RandomizedQueue<String> rngQueue = new RandomizedQueue<String>();
        for (int i = 1; i < argumentCount; i++)
            rngQueue.enqueue(args[i]);

        int counter = 0;
        for (String string : rngQueue) {
            counter++;
            System.out.println(string);
            if (counter >= permutationCount)
                break;
        }
    }
}
