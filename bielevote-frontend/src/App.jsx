import { useState } from "react";

export default function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p className="text-green-700">
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
    </>
  );
}
