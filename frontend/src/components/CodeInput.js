import React, { useState } from "react";
import axios from "axios";
import "./CodeInput.css";

function CodeInput({ setFeedback }) {
  const [code, setCode] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8081/api/code-review",
        { code },
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      const messageContent = response.data.choices[0]?.message?.content || "No feedback received.";
      setFeedback(messageContent);
    } catch (error) {
      console.error("Error submitting code:", error);
      setFeedback("An error occurred while analyzing the code.");
    }
  };

  return (
    <div className="CodeInputWrapper">
      <form onSubmit={handleSubmit}>
        <textarea
          className="CodeTextarea"
          rows="10"
          cols="50"
          value={code}
          onChange={(e) => setCode(e.target.value)}
          placeholder="Enter your Java code here..."
        />
        <br />
        <button className="SubmitButton" type="submit">Submit Code</button>
      </form>
    </div>
  );
}

export default CodeInput;
