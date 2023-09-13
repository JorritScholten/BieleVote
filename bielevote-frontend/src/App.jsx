import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import ProjectWritingPage from "./page/project/ProjectWritingPage";
import NewsOverview from "./page/news/NewsOverview";
import NewsArticlePage from "./page/news/NewsArticlePage";

export default function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="*" element={<h1>Not Found</h1>} />
          <Route path="/news" element={<NewsOverview />} />
          <Route path="/news/:articleId" element={<NewsArticlePage />} />
          <Route path="/projectwritingpage" element={<ProjectWritingPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
