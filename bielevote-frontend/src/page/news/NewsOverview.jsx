import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { BsFillCalendarWeekFill } from "react-icons/bs";
import { IoReturnDownBack } from "react-icons/io5";
import { Pagination } from "semantic-ui-react";

import { emptyForms } from "../../misc/ApiForms";
import Header from "../../components/Header";
import { backendApi } from "../../misc/ApiMappings";

export default function NewsOverview() {
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
    <>
      <Header pageTitle="News" />
      <div className="flex m-auto text-6xl ml-10">
        <Link to={"/"}>
          <IoReturnDownBack />
        </Link>
      </div>
      <div className="flex flex-col items-center justify-center">
        <div className="flex flex-col  w-3/5">
          {newsList.articles.map((articlePreview) => (
            <div className="p-3 flex flex-col" key={articlePreview.id}>
              <Link to={"/news/" + articlePreview.id}>
                <div className="text-3xl text-blue-700 font-bold underline">
                  {articlePreview.title}
                </div>
              </Link>
              <div className="flex flex-row">
                <div className="mr-3 text-gray-600">
                  {articlePreview.datePlaced}
                </div>
                <div className="flex items-center">
                  <BsFillCalendarWeekFill />
                </div>
              </div>
              <div className="text-lg text-gray-800 font-semibold">
                {articlePreview.summaryPreview}
              </div>
            </div>
          ))}
        </div>
        <div className="">
          <Pagination
            defaultActivePage={1}
            totalPages={newsList.totalPages}
            onPageChange={handlePageChange}
          />
        </div>
      </div>
    </>
  );
}
