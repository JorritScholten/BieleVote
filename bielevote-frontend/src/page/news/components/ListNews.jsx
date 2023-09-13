import { useState } from "react";

export default function ListNews(newsList) {
  const [loading, setLoading] = useState(true);
  return (
    <>
      {newsList == []
        ? loading && <div>loading...</div>
        : setLoading(false) && <div></div>}
    </>
  );
}
