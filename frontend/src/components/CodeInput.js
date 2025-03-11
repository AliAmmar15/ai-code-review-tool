import React, { useState } from "react";
import axios from "axios";

function CodeInput() {
  const [code, setCode] = useState("");
  const [feedback, setFeedback] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/code-review", { code });
      setFeedback(response.data.feedback);
    } catch (error) {
      console.error("Error submitting code:", error);
    }
  };

  return (
    <div>
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
          <h2>Feedback</h2>
          <pre>{feedback}</pre>
        </div>
      )}
    </div>
  );
}

export default CodeInput;
