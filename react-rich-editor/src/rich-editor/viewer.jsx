import React, {useEffect, useRef, useState} from 'react';
import {View} from '@aomao/engine';
import {cards, pluginConfig, plugins} from './config.jsx';

import './App.css'
import 'antd/dist/antd.min.css'

function Viewer({content}) {
    //编辑器容器
    const ref = useRef(null);
    //引擎实例
    const [view, setView] = useState();

    useEffect(() => {

        if (!ref.current) return;
        //实例化引擎
        const view = new View(ref.current, {
            plugins:  plugins,
            cards: cards,
            config: pluginConfig,
        })

        setView(view)
    }, []);

    useEffect(() => {
        if (view && content) {
            view.render(content);
        }
    }, [content, view])

    return (
        <div>
            <div ref={ref} />
        </div>
    );
}

export default Viewer;
