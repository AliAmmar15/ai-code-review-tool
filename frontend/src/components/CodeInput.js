import React, { useState } from "react";
import axios from "axios";

function CodeInput() {
  const [code, setCode] = useState("");
  const [feedback, setFeedback] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8081/api/code-review/analyze", code, {
        headers: { "Content-Type": "application/json" },
      });

      setFeedback(response.data); // OpenAI response is directly returned as a string
    } catch (error) {
      console.error("Error submitting code:", error);
      setFeedback("An error occurred while analyzing the code.");
    }
  };

  return (
    <div>
      <h2>Code Review</h2>
      <form onSubmit={handleSubmit}>
        <textarea
          rows="10"
          cols="50"
          value={code}
          onChange={(e) => setCode(e.target.value)}
          placeholder="Enter your Java code here"
        />
        <br />
        <button type="submit">Submit Code</button>
      </form>
      {feedback && (
        <div>
          <h2>AI Feedback</h2>
          <pre>{feedback}</pre>
        </div>
      )}
    </div>
  );
}

export default CodeInput;
