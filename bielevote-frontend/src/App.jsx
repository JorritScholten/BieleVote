import { BrowserRouter, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./misc/AuthContext";
import { navList } from "./misc/NavMappings";
import PrivateRoute from "./misc/PrivateRoute";

export default function App() {
  return (
    <>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            {navList.map((nav) => (
              <Route
                key={nav.path}
                path={nav.path}
                element={
                  <PrivateRoute
                    page={nav.element()}
                    allowedAccountTypes={nav.allowedAccountTypes}
                  />
                }
              />
            ))}
            <Route path="*" element={<h1>Not Found</h1>} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </>
  );
}
