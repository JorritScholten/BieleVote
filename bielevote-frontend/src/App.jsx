import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import ProjectWritingPage from "./components/ProjectWritingPage";

export default function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="*" element={<h1>Not Found</h1>} />
          <Route path="projectwritingpage" element={<ProjectWritingPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
