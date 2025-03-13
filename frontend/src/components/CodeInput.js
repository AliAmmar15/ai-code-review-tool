import React, { useState } from "react";
import axios from "axios";
import "./CodeInput.css"; // Import the CSS file

function CodeInput() {
  const [code, setCode] = useState("");
  const [feedback, setFeedback] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8081/api/code-review",
        { code }, // Send as an object
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      // Extract the AI-generated content from the response
      const messageContent = response.data.choices[0]?.message?.content || "No feedback received.";
      setFeedback(messageContent);
    } catch (error) {
      console.error("Error submitting code:", error);
      setFeedback("An error occurred while analyzing the code.");
    }
  };

  return (
    <div className="CodeReview">
      <h2>Code Review</h2>
      <form onSubmit={handleSubmit}>
        <textarea
          className="CodeInput-textarea"
          rows="10"
          cols="50"
          value={code}
          onChange={(e) => setCode(e.target.value)}
          placeholder="Enter your Java code here"
        />
        <br />
        <button className="CodeInput-button" type="submit">
          Submit Code
        </button>
      </form>
      {feedback && (
        <div className="AIFeedback">
          <h2>AI Feedback</h2>
          <p>{feedback}</p>
        </div>
      )}
    </div>
  );
}

export default CodeInput;
