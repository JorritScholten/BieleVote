import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import ProjectWritingPage from "./page/project/ProjectWritingPage";
import { AuthProvider } from "./misc/AuthContext";
import CreateAccountPage from "./page/newAccount";
import ProjectOverviewPage from "./page/projectOverview/ProjectOverviewPage";

export default function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/new-account" element={<CreateAccountPage />} />
            <Route path="/projectwritingpage" element={<ProjectWritingPage />} />
            <Route path="/projectoverview" element={<ProjectOverviewPage />} />
            <Route path="*" element={<h1>Not Found</h1>} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}
