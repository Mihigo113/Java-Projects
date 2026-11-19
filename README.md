# Smart Restaurant Order System

A beginner-friendly Java console application for calculating a restaurant customer's bill.

## Requirements covered
- Customer name and age
- Meal price and number of drinks
- Drinks cost at $15.50 each
- Student discount (10%) or senior discount (5% for age 60+)
- Only one discount; the larger discount is used
- 10% tip for service ratings of 4 or 5
- Rating validation (must be 1 to 5)
- Receipt showing costs, discount, tip, exact final bill, and whole-number amount

## Java concepts used
- Variables and constants
- `String`, `int`, `double`, and `boolean`
- `Scanner` keyboard input
- Arithmetic and type casting
- `if`, `else if`, and `else`
- Comparison and logical operators

## Restrictions
The program uses no loops, arrays, or extra methods.

## How to compile and run
You need a Java JDK installed.

```bash
javac Main.java
java Main
```

## Example test orders

### Test 1: Student customer
- Name: Amina
- Age: 22
- Meal price: 40
- Drinks: 2
- Student: true
- Service rating: 5

Expected calculation:
- Drinks: $31.00
- Subtotal: $71.00
- Student discount: $7.10
- Tip: $6.39
- Final bill: $70.29
- Whole amount (Java int cast): $70

### Test 2: Senior customer
- Name: Jean
- Age: 65
- Meal price: 100
- Drinks: 1
- Student: false
- Service rating: 3

Expected calculation:
- Drinks: $15.50
- Subtotal: $115.50
- Senior discount: $5.78 (displayed to two decimal places)
- Tip: $0.00
- Final bill: $109.73
- Whole amount (Java int cast): $109

Note: Casting a positive `double` to `int` removes the decimal part; it does not round to the nearest integer.
