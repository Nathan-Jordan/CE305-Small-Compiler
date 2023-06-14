def fibonacciNumbers(n):
	print("Fibonacci numbers:")

	previous = 0
	current = 1
	index = 1

	while index <= n:
		print(current)
		temp = current
		current += previous
		previous = temp
		index = index + 1

	print("")


def factorial(n):
	print("Factorial number of " + str(n) + ":")

	val = 1
	counter = 1

	while counter <= n:
		val *= counter
		counter = counter + 1

	return str(val) + "\n"

def oddEven(n):
	print("Odd, even and sum:")

	odd = 0
	even = 0

	index = 1
	while index <= n:
		if index % 2 == 0:
			even = even + index
		else:
			odd = odd + index
		index = index + 1
	print(odd)
	print(even)
	print(odd + even)


fibonacciNumbers(12)
print(factorial(8))
oddEven(100)
