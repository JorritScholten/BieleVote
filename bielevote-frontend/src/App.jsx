import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import ProjectWritingPage from "./page/project/ProjectWritingPage";
import NewsPageList from "./page/news/NewsPageList";
import NewsPage from "./page/news/NewsPage";

export default function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="*" element={<h1>Not Found</h1>} />
          <Route path="/news" element={<NewsPageList />} />
          <Route path="/news/:articleId" element={<NewsPage />} />
          <Route path="/projectwritingpage" element={<ProjectWritingPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
