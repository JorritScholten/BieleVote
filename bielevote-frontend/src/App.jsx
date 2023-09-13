import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./page/home";
import ProjectWritingPage from "./page/project/ProjectWritingPage";
import { AuthProvider } from "./misc/AuthContext";
import CreateAccountPage from "./page/newAccount";
import { navList } from "./misc/NavMappings";

export default function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            {navList.map((nav) => (
              <Route key={nav.id} path={nav.path} element={nav.element()} />
            ))}
            <Route path="*" element={<h1>Not Found</h1>} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}
