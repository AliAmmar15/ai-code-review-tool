import React, { useState } from "react";
import "./App.css";
import CodeInput from "./components/CodeInput";

function App() {
  const [feedback, setFeedback] = useState("Waiting for AI feedback...");

  return (
    <div className="App">
      <header className="App-header">
        <h1>BugBuster</h1>
      </header>
      <main className="App-main">
        <div className="CodeContainer">
          <h2 className="section-title">Code Review</h2>
          <CodeInput setFeedback={setFeedback} />
        </div>
        <div className="FeedbackContainer">
          <h2 className="section-title">AI Feedback</h2>
          <div className="FeedbackBox">
            <pre>{feedback}</pre>
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
