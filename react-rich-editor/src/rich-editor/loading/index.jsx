import React from 'react';
import Spin from 'antd/es/spin';
import 'antd/es/spin/style';



const Loading = ({ text, loading, children }) => {
	return (
		<Spin className="loading" tip={text} spinning={loading}>
			{children}
		</Spin>
	);
};

export default Loading;
