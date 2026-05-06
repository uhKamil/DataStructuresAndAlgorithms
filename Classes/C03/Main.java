public class Main {
    public static void main(String[] args) {
        // Task 1
//        VTS<Integer> stack = new VTS<>();
//
//        System.out.println("--- 1. Testing Push & Auto-Cursor Reset ---");
//        stack.push(10);
//        stack.push(20);
//        stack.push(30);
//        // Cursor should automatically be at the top (30)
//        System.out.println("Peek after pushes (Expected 30): " + stack.peek());
//
//        System.out.println("\n--- 2. Testing Down & Peek ---");
//        System.out.println("Moving down (Expected 20): " + stack.down());
//        System.out.println("Peek current cursor (Expected 20): " + stack.peek());
//        System.out.println("Moving down again (Expected 10): " + stack.down());
//
//        System.out.println("\n--- 3. Testing Bottom Boundary ---");
//        try {
//            stack.down(); // Cursor is at 10 (index 0), this should fail
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Bottom reached correctly! Exception caught: " + e.getMessage());
//        }
//
//        System.out.println("\n--- 4. Testing Top ---");
//        System.out.println("Resetting to top (Expected 30): " + stack.top());
//        System.out.println("Peek after top() (Expected 30): " + stack.peek());
//
//        System.out.println("\n--- 5. Testing Pop & Auto-Cursor Reset ---");
//        // We move the cursor away from the top first to prove pop() resets it
//        stack.down();
//        System.out.println("Cursor moved down to: " + stack.peek());
//        System.out.println("Popping top element (Expected 30): " + stack.pop());
//        // After popping 30, the new top is 20. The cursor MUST auto-reset to 20.
//        System.out.println("Peek after pop (Expected 20): " + stack.peek());
//
//        System.out.println("\n--- 6. Traverse Remaining Stack ---");
//        System.out.println("Remaining items (Expected 10, then 20):");
//        stack.traverse();

        SinkingStackA<Integer> Stack1 = new SinkingStackA<>(5);
        Stack1.push(1);
        Stack1.push(2);
        Stack1.push(3);
        Stack1.push(4);
        Stack1.push(5);
        // This push should fail as the stack is full
        Stack1.push(6);

        System.out.println("Popping all elements:");
        for (int i = 0; i < 5; i++) {
            System.out.println(Stack1.pop());
        }
        // This pop should fail as the stack is empty
        System.out.println("Attempting to pop from empty stack:");
        System.out.println(Stack1.pop());
    }
}