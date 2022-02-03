void main(){
  print("Hello World");
}

//install dart sdk or flutter- which somes with dart

//Run with
//dart hello_world.dart

//or install coderunner to run 

main() => print("Hello World");

main() => 1;
//right side can be an expression

//Dart VM is JIT( just in time compiler)

//dart is a cli
//dart run launches a VM
//dart compile can compile to different formats

//node out.js can run dart js file with nodejs

//dart2native and dart2js were earlier diff tools 

//like typescript transpiles to js, same is with dart

//dart is single threaded but can be mutiple threaded with multiple isolates

//Smalltalk, Python, Dart - everything is an object - no native data type

void main(){
  int i =5;
  //String interpolations are built in
  print("$i,${i.runtimeType}")
  //native type looking types are actually classes starting with lower case - int, bool, 
  //double are all classes
  var x = "a"
  //here var type is automatically inferred 
}

//Dynamic nature of dart
//dart is compiled and check types- catches compile time issues

int add(int a, int b){
  int c = a+b;
  return c;
}

dynamic add(dynamic a, dynamic b){}
dynamic add(dynamic a, int b){}

void main(){
  add(1, "2") //will give error
  //when type is omitted "dynamic" type is considered
}

//Collections
List ls = new List(); //Not required

var list = [1,2,3];
print(list.runtimeType); // List<int>
list.add("3") //gives error

var list = [1,2,"3"];
print(list.runtimeType); // List<Object>
list.add("3") //no issue

var list = const[1,2,3]; //now list cannot be modified
var list = <int>[1,2,3]; //no auto inferring and no other type can be added

//Dart has operator oerriding, but functions cannot be overloaded

//dart is functional, procedural, object oriented

//procedural - no need to define classes

//Python, C++ only allows multiple inheritance 
//mixin is like a class but it not base class, it is just a helper class
//Derived class is inherited from Base class

//no interface keyword
//every class acts as an inerface

//Mirrors is like reflection in Java - talk to any class at run time and know everything 
//abt that class
//Instantiate classes at runtime

//Future object is like Promise object for asynchronous operation

//Java can tell through Reflection API that it is a list but cannot tell 
//it is list of integers as generics were introduced late in java

//Java supports refelction without generics
//Dart supports reflection with geenrics

//mixin is basically interface with default methods

