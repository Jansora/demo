import React from "react";
import {BookData} from "@/lib/declares/config";
import fs from "fs";
import {ByteViewer} from "@jansora/bytemd/esm/Viewer";

export default function RenderDoc({data, params, resourcePath}: {data: BookData, params: any, resourcePath: string}) {

    // const { name, path } = data;
    const { category, book, doc } = params;

    const content = fs.readFileSync(data.path, 'utf8');
    // const fileName = path.basename(data.path);

    // if (data.suffix === ".pdf") {
    //     return <Document file={`//category/${category}/book/${book}/doc/${STATIC}/${fileName}`} />
    // }

    return <ByteViewer value={content} />
}



