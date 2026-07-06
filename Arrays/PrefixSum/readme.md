# Prefix Sum + HashMap Patterns

## Core Formula

```text
SubarraySum(L..R)
=
PrefixSum(R) - PrefixSum(L-1)
```

Most interview questions are variations of this formula.

---

## Pattern Decision Tree

### Sum = K

Condition:

```text
currentPrefix - previousPrefix = K
```

Store:

```text
PrefixSum -> Frequency
```

Lookup:

```text
currentPrefix - K
```

Example:

```text
LC 560 - Subarray Sum Equals K
```

---

### Sum Divisible By K

Condition:

```text
(currentPrefix - previousPrefix) % K == 0
```

Equivalent:

```text
currentPrefix % K == previousPrefix % K
```

Store:

```text
Remainder -> Frequency
```

Lookup:

```text
Same Remainder
```

Example:

```text
LC 974 - Subarray Sums Divisible By K
```

---

### Check Existence

Store:

```text
Remainder -> First Index
```

Example:

```text
LC 523
```

---

### Maximum Length

Store:

```text
PrefixSum -> First Index
```

Example:

```text
LC 325
```

---

## map.put(0,1)

Used in counting problems.

Meaning:

```text
Prefix sum 0 occurred once
before array started.
```

Helps count:

```text
subarrays beginning at index 0
```

Example:

```text
nums = [3]
k = 3
```

---

## map.put(0,-1)

Used in length/index problems.

Meaning:

```text
Prefix sum 0 exists at
imaginary index -1.
```

Helps calculate:

```text
subarray length correctly
when it starts at index 0
```

---

## Divisible By K Intuition

### rem == 0

```text
PrefixSum % K == 0
```

Means:

```text
sum(0...i)
```

is divisible by K.

Subarray starts from index 0.

---

### Same Remainder

```text
PrefixA % K == PrefixB % K
```

Means:

```text
(PrefixA - PrefixB) % K == 0
```

Therefore:

```text
subarray between them
is divisible by K
```

---

## Negative Modulo Trick

Java:

```java
-2 % 5 = -2
```

Math:

```text
-2 ≡ 3 (mod 5)
```

Normalize:

```java
int rem = ((prefixSum % k) + k) % k;
```

Use whenever negative numbers are possible.

---

## Memory Map

```text
Sum = K
=> Store Prefix Sum
=> Lookup PrefixSum - K

Divisible By K
=> Store Remainder
=> Lookup Same Remainder

Count
=> Store Frequency

Existence
=> Store First Index

Longest Length
=> Store First Index

Repeated Prefix Sum
=> Zero Sum Subarray

Repeated Remainder
=> Sum Divisible By K
```
