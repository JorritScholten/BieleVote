import { useState, useEffect } from "react";
import { Pagination } from "semantic-ui-react";
import { emptyForms } from "../../misc/ApiForms";
import Header from "../../components/Header";
import { backendApi } from "../../misc/ApiMappings";
import ListNews from "./components/ListNews";

export default function NewsOverviewPage() {
  const [newsList, setNewsList] = useState(emptyForms.newsOverview);

  useEffect(() => {
    handlePageChange();
  }, []);

  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    const response = await backendApi.getAllNewsArticles(page, 3);
    setNewsList(response.data);
  };

  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="News" />
      <div className="flex flex-col items-center justify-center">
        <ListNews newsList={newsList} />
        <div className="">
          <Pagination
            defaultActivePage={1}
            totalPages={newsList.totalPages}
            onPageChange={handlePageChange}
          />
        </div>
      </div>
    </div>
  );
}
