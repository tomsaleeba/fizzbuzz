// FizzBuzz in JavaScript
// Author: Tom Saleeba
const strategies = [
  {
    condition: function isDivisibleBy3(testValue) {
      return buildIsDivisibleByFn(3)(testValue)
    },
    action: function() {
      console.log('fizz')
    },
  },
  {
    condition: function isDivisibleBy5(testValue) {
      return buildIsDivisibleByFn(5)(testValue)
    },
    action: function() {
      console.log('buzz')
    },
  },
  {
    condition: function catchAll() {
      return true
    },
    action: function(testValue) {
      console.log(testValue)
    },
  },
]

let counter = 1
while (counter <= 100) {
  const strategy = (() => {
    for (const curr of strategies) {
      if (curr.condition(counter)) {
        return curr.action
      }
    }
    throw new Error(
      `Programmer problem: no strategy to handle value='${counter}'`,
    )
  })()
  strategy(counter)
  counter++
}

function buildIsDivisibleByFn(divisor) {
  return function(testValue) {
    return testValue % divisor === 0
  }
}
