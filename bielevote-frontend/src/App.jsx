import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import ProjectWritingPage from "./page/project/ProjectWritingPage";
import NewsOverview from "./page/news/NewsOverview";
import NewsArticlePage from "./page/news/NewsArticlePage";
import { AuthProvider } from "./misc/AuthContext";
import CreateAccountPage from "./page/newAccount";

export default function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/new-account" element={<CreateAccountPage />} />
            <Route
              path="/projectwritingpage"
              element={<ProjectWritingPage />}
            />
            <Route path="/news" element={<NewsOverview />} />
            <Route path="/news/:articleId" element={<NewsArticlePage />} />
            <Route path="*" element={<h1>Not Found</h1>} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}
