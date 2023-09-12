import { useState } from "react";
import Header from "../../components/Header";
import { Link } from "react-router-dom";

export default function HomePage() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <Header pageTitle="Home page" />
      <Link to="/news">
        <div className="bg-blue-800 p-3">News</div>
      </Link>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p className="text-green-700">
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
    </div>
  );
}
