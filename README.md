# Toy Language Project

This repository demonstrates the iterative development of a toy programming language. Each folder corresponds to a specific iteration, showcasing progress in syntax design, compiler construction, and runtime behavior.

## Structure
- **Iteration 1**: Initial setup, including basic syntax and parsing.
- **Iteration 2**: Enhanced with basic semantic analysis and error handling.
- **Iteration 3**: Implemented features for managing heaps, garbage collection, and supporting the `while` statement in the ToyLanguage interpreter.
- **Iteration 4**: Added support for concurrent execution in ToyLanguage using a `fork` statement. Multiple `PrgStates` can be executed concurrently using threads.
- **Iteration 5**: Implemented a **Type Checker** for ToyLanguage programs. The type checker validates expressions and statements during program execution.
- **Iteration 6**: Implemented a **JavaFX GUI** that allows users to interact with the interpreter. The GUI displays the following information:, Number of `PrgStates`, `HeapTable`, `Out`, `FileTable`, `SymTable` and `ExeStack` for the selected `PrgState`, A button to execute one step for all `PrgStates`.
