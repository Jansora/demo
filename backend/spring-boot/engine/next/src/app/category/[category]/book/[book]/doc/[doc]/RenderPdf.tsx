'use client';


import React, {useCallback, useState} from "react";
import {BookData} from "@/lib/declares/config";

import {Document, Page, pdfjs} from 'react-pdf';
import {useResizeObserver} from '@wojtekmaj/react-hooks';
import 'react-pdf/dist/esm/Page/AnnotationLayer.css';
import 'react-pdf/dist/esm/Page/TextLayer.css';

import '@/lib/css/pdf.css';


import type {PDFDocumentProxy} from 'pdfjs-dist';

// pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.min.js`

pdfjs.GlobalWorkerOptions.workerSrc = new URL(
    // 'pdfjs-dist/build/pdf.worker.min.js',
    'pdfjs-dist/legacy/build/pdf.worker.min.js',

    import.meta.url,
).toString();

// const options = {
//     cMapUrl: '/cmaps/',
//     standardFontDataUrl: '/standard_fonts/',
// };

// import * as PDFJS from 'pdfjs-dist/build/pdf.min.mjs'
// import * as pdfjs from 'pdfjs-dist/webpack';

// const pdfjsSrc = await import('pdfjs-dist/build/pdf');
// const pdfjsWorker = await import('pdfjs-dist/build/pdf.worker.entry');
// pdfjsSrc.GlobalWorkerOptions.workerSrc = pdfjsWorker;
//
// PDFJS.GlobalWorkerOptions.workerSrc = new URL(
//     'pdfjs-dist/build/pdf.worker.min.js',
//     import.meta.url,
// ).toString();
// pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.min.js`;
const options = {
    cMapUrl: '/cmaps/',
    standardFontDataUrl: '/standard_fonts/',
};

const resizeObserverOptions = {};

const maxWidth = 800;

type PDFFile = string | File | null;


export default function RenderPdf({data, params, resourcePath}: {data: BookData, params: any, resourcePath: string}) {

    // const { name, path } = data;

    const { category, book, doc } = params;

    const [file, setFile] = useState<PDFFile>(resourcePath);
    const [numPages, setNumPages] = useState<number>();
    const [containerRef, setContainerRef] = useState<HTMLElement | null>(null);
    const [containerWidth, setContainerWidth] = useState<number>();

    const onResize = useCallback<ResizeObserverCallback>((entries) => {
        const [entry] = entries;

        if (entry) {
            setContainerWidth(entry.contentRect.width);
        }
    }, []);

    useResizeObserver(containerRef, resizeObserverOptions, onResize);

    function onFileChange(event: React.ChangeEvent<HTMLInputElement>): void {
        const { files } = event.target;

        if (files && files[0]) {
            setFile(files[0] || null);
        }
    }

    function onDocumentLoadSuccess({ numPages: nextNumPages }: PDFDocumentProxy): void {
        setNumPages(nextNumPages);
    }

    return (
        <div className="Example">
            <div className="Example__container">
                <div className="Example__container__document" ref={setContainerRef}>
                    <Document file={file} onLoadSuccess={onDocumentLoadSuccess} options={options}>
                        {Array.from(new Array(numPages), (el, index) => (
                            <Page
                                key={`page_${index + 1}`}
                                pageNumber={index + 1}
                                width={containerWidth ? Math.min(containerWidth, maxWidth) : maxWidth}
                            />
                        ))}
                    </Document>
                </div>
            </div>
        </div>
    );
}