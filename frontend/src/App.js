import React from "react";
import "./App.css";
import CodeInput from "./components/CodeInput";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>AI-Powered Code Review Tool</h1>
      </header>
      <main>
        <CodeInput />
      </main>
    </div>
  );
}

export default App;
