import HomePage from "../page/home";
import CreateAccountPage from "../page/newAccount";
import ProjectWritingPage from "../page/project";

const accountTypes = {
  admin: "ADMINISTRATOR",
  munic: "MUNICIPAL",
  citiz: "CITIZEN",
};

export const navList = [
  {
    id: 0,
    path: "/",
    name: "Home",
    requiresLogin: false,
    hideFromMenu: false,
    element: () => <HomePage />,
  },
  {
    id: 1,
    path: "/new-account",
    name: "New account",
    requiresLogin: false,
    hideFromMenu: true,
    element: () => <CreateAccountPage />,
  },
  {
    id: 2,
    path: "/projectwritingpage",
    name: "New Project",
    requiresLogin: true,
    hideFromMenu: false,
    element: () => <ProjectWritingPage />,
  },
];
